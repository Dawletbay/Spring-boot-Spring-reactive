package com.best.company.integration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import com.best.company.dto.auth.TokenDTO;
import com.best.company.dto.integration.best.BankListCtoDTO;
import com.best.company.dto.integration.best.LoginDTO;
import com.best.company.dto.integration.best.SyncBankListCtoResponseDTO;
import com.best.company.dto.integration.best.SyncCompanyTinListResponseDTO;
import com.best.company.exceptions.BadRequestException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 27.07.2023 14:47
 */
@Component
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
public class TestClient extends BaseClient {

    static LocalDateTime authenticateRefreshTime;
    static String tokenStore;
    @NonFinal
    Map<String, Integer> pageData;

    public TestClient(Map<String, Integer> pageData) {
        this.pageData = pageData;
    }

    @PreDestroy
    private void clearCache() {
        pageData.clear();
        pageData = null;
    }

    @PostConstruct
    public void init() {
        pageData.put("totalElements", 0);
        pageData.put("totalPages", 0);
        pageData.put("pageCount", 0);
        pageData.put("limit", 500);
    }

    /*
     * START
     * SYNCHRONIZATION
     * Bank
     * */
    public Flux<BankListCtoDTO> getBanks() {
        return getToken()
                .flatMapMany(this::getBanksData);
    }

    private Flux<BankListCtoDTO> getBanksData(String token) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(applicationProperties.getBestConfig().getBankListUrl())
                .queryParam("page", 0)
                .queryParam("size", 10000)
                .queryParam("orderBy", "parent_id")
                .queryParam("sortOrder", "desc");

        return webClient
                .get()
                .uri(builder.toUriString())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToFlux(SyncBankListCtoResponseDTO.class)
                .flatMap(this::getBankList);
    }

    private Flux<BankListCtoDTO> getBankList(SyncBankListCtoResponseDTO syncBankListCtoResponseDTO) {
        return Flux.fromIterable(syncBankListCtoResponseDTO.getContent());
    }
    /*
     * END
     * SYNCHRONIZATION
     * Bank
     * */


    /*
     * START
     * SYNCHRONIZATION
     * Company TINs
     * */
    public Flux<Boolean> getTins() {
        pageData.put("totalElements", 0);
        pageData.put("totalPages", 0);
        pageData.put("pageCount", 0);
        return getToken()
                .flatMapMany(this::getTins)
                .flatMap(this::getTinsPagination);
    }

    private Flux<Boolean> getTinsPagination(List<String> tinList) {
        Flux.range(pageData.get("pageCount"), pageData.get("totalPages"))
                .subscribeOn(Schedulers.boundedElastic())
                .concatMap(index -> {
                    pageData.put("pageCount", index + 1);
                    return getToken()
                            .flatMapMany(this::getTins);
                }).subscribe();
        return Flux.just(Boolean.TRUE);
    }

    private Flux<List<String>> getTins(String token) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(applicationProperties.getBestConfig().getCompanyListUrl())
                .queryParam("page", pageData.get("pageCount"))
                .queryParam("size", pageData.get("limit"));

        return webClient
                .get()
                .uri(builder.toUriString())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToFlux(SyncCompanyTinListResponseDTO.class)
                .onErrorContinue((e, taxGap) -> log.error("GET Company TINs response: {}", e.getMessage()))
                .subscribeOn(Schedulers.boundedElastic())
                .concatMap(this::changePagination)
                .concatMap(this::sendDataToRabbitMq);
    }

    private Flux<SyncCompanyTinListResponseDTO> changePagination(SyncCompanyTinListResponseDTO companyTinResponseDTO) {
        if (Objects.nonNull(companyTinResponseDTO) && Objects.nonNull(companyTinResponseDTO.getTotalElements()) && Objects.nonNull(companyTinResponseDTO.getTotalPages())) {
            pageData.put("totalElements", companyTinResponseDTO.getTotalElements().intValue());
            pageData.put("totalPages", companyTinResponseDTO.getTotalPages().intValue() - 1);
            return Flux.just(companyTinResponseDTO);
        } else {
            return Flux.just(new SyncCompanyTinListResponseDTO());
        }
    }

    private Flux<List<String>> sendDataToRabbitMq(SyncCompanyTinListResponseDTO companyTinResponseDTO) {
        if (Objects.isNull(companyTinResponseDTO)) {
            log.error("Best Company TIN list is empty!");
            return Flux.just(new ArrayList<>());
        } else {
            return Flux.just(companyTinResponseDTO.getContent())
                    .flatMap(tinList -> {
                        AtomicInteger delay = new AtomicInteger(0);
                        rabbitMqCompanyProducer
                                .syncTins(companyTinResponseDTO.getContent(), delay.incrementAndGet());
                        return Flux.just(tinList);
                    });
        }
    }
    /*
     * END
     * SYNCHRONIZATION
     * Company TINs
     * */

    public Mono<String> getToken() {
        if (tokenStore != null && authenticateRefreshTime != null && authenticateRefreshTime.isAfter(LocalDateTime.now()))
            return Mono.just(tokenStore);

        try {
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(applicationProperties.getBestConfig().getUsername());
            loginDTO.setPassword(applicationProperties.getBestConfig().getPassword());
            loginDTO.setRememberMe(true);

            return webClient.post()
                    .uri(applicationProperties.getBestConfig().getAuthUrl())
                    .bodyValue(loginDTO)
                    .headers(getHeaders(null, null))
                    .retrieve()
                    .bodyToMono(TokenDTO.class)
                    .doOnSuccess(tokenDTO -> tokenStore = tokenDTO.getAccessToken())
                    .map(this::getToken);
        } catch (Exception e) {
            tokenStore = null;
            throw new BadRequestException(e.getMessage());
        }
    }

    private String getToken(TokenDTO token) {
        authenticateRefreshTime = LocalDateTime.now().plusSeconds(2592000);
        tokenStore = Objects.requireNonNull(token).getAccessToken();
        return tokenStore;
    }
}
