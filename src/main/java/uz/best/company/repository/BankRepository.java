package uz.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.best.company.domain.Bank;

import java.util.UUID;

@Repository
public interface BankRepository extends R2dbcRepository<Bank, UUID> {

    Mono<Bank> findByMfo(String mfo);

    Flux<Bank> findAllByDeletedIsFalse();

    Mono<Bank> findByParentId(UUID parentId);
}
