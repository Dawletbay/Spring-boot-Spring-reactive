package com.best.company.service;

import com.best.company.integration.TestClient;
import com.best.company.repository.BankRepository;
import com.best.company.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.best.company.domain.Bank;
import com.best.company.dto.bank.BankDTO;
import com.best.company.dto.integration.best.BankListDTO;

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
     * From Somewhere
     * */
    public Mono<Void> sync() {
        return testClient
                .getBanks()
                .flatMap(this::save)
                .onErrorContinue((e, bank) -> log.error(e.getMessage(), e))
                .then();
    }

    private Mono<Bank> save(BankListDTO bankListDTO) {
        return bankRepository.findByMfo(bankListDTO.getMfo())
                .flatMap(bank -> this.save(bank, bankListDTO))
                .switchIfEmpty(this.save(new Bank(), bankListDTO));
    }

    private Mono<Bank> save(Bank bank, BankListDTO bankListDTO) {
        bank.setNameEn(bankListDTO.getName());
        bank.setCode(bankListDTO.getCategoryCode());
        bank.setMfo(bankListDTO.getMfo());
        bank.setTin(bankListDTO.getTin());
        if (StringUtils.isNotEmpty(bankListDTO.getParentCode())) {
            return bankRepository
                    .findByMfo(bankListDTO.getParentCode())
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
     * From Somewhere
     * */
}
