package uz.best.company.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.best.company.integration.TestClient;
import uz.best.company.repository.BankRepository;
import uz.best.company.service.base.BaseService;
import uz.best.company.domain.Bank;
import uz.best.company.dto.bank.BankDTO;
import uz.best.company.dto.integration.best.BankListCtoDTO;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 01.08.2023 17:30
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
@Slf4j
public class BankService extends BaseService {

    TestClient testClient;
    BankRepository bankRepository;

    public Mono<BankDTO> getBankByMfo(String mfo) {
        return bankRepository.findByMfo(mfo)
                .map(Bank::toDTO)
                .switchIfEmpty(Mono.just(new BankDTO()))
                .onErrorContinue((e, bank) -> log.error(e.getMessage(), e));
    }

    public Mono<BankDTO> get(UUID id) {
        return bankRepository.findById(id)
                .map(Bank::toDTO)
                .switchIfEmpty(Mono.just(new BankDTO()))
                .onErrorContinue((e, bank) -> log.error(e.getMessage(), e));
    }

    public Flux<BankDTO> getList() {
        return bankRepository
                .findAllByDeletedIsFalse()
                .map(Bank::toDTO)
                .switchIfEmpty(Mono.just(new BankDTO()))
                .onErrorContinue((e, bank) -> log.error(e.getMessage(), e));
    }

    public Mono<Bank> getByMfo(String mfo) {
        return bankRepository.findByMfo(mfo);
    }

    /*
     * START
     * SYNCHRONIZATION
     * BANK Data
     * From CTS
     * */
    public Mono<Void> sync() {
        return testClient
                .getBanks()
                .flatMap(this::save)
                .onErrorContinue((e, bank) -> log.error(e.getMessage(), e))
                .then();
    }

    private Mono<Bank> save(BankListCtoDTO bankListCtoDTO) {
        return bankRepository.findByMfo(bankListCtoDTO.getCode())
                .flatMap(bank -> this.save(bank, bankListCtoDTO))
                .switchIfEmpty(this.save(new Bank(), bankListCtoDTO));
    }

    private Mono<Bank> save(Bank bank, BankListCtoDTO bankListCtoDTO) {
        bank.setNameEn(bankListCtoDTO.getName());
        bank.setCode(bankListCtoDTO.getCategoryCode());
        bank.setMfo(bankListCtoDTO.getCode());
        bank.setTin(bankListCtoDTO.getTin());
        if (StringUtils.isNotEmpty(bankListCtoDTO.getParentCode())) {
            return bankRepository
                    .findByMfo(bankListCtoDTO.getParentCode())
                    .flatMap(parentBank -> {
                        bank.setParentId(parentBank.getId());
                        return bankRepository.save(bank);
                    });
        }
        return bankRepository.save(bank);
    }
    /*
     * END
     * SYNCHRONIZATION
     * BANK Data
     * From CTS
     * */
}
