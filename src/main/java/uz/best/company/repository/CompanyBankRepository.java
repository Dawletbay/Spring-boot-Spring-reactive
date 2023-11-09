package uz.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.best.company.domain.company.CompanyBank;

import java.util.UUID;

@Repository
public interface CompanyBankRepository extends R2dbcRepository<CompanyBank, UUID> {
    Mono<CompanyBank> findByCompanyIdAndBankId(UUID companyId, UUID bankId);

    Mono<CompanyBank> findByCompanyId(UUID companyId);

    Flux<CompanyBank> findAllByDeletedIsFalse();
}
