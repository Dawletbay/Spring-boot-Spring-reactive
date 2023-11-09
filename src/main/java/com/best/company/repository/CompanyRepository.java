package com.best.company.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.best.company.domain.company.Company;

import java.util.UUID;

@Repository
public interface CompanyRepository extends R2dbcRepository<Company, UUID> {

    Flux<Company> findAllByDeletedIsFalseOrderByCreatedDateAsc();

    Mono<Company> findByTin(String tin);

    @Query("SELECT c.id FROM company c WHERE c.deleted is not true")
    Flux<UUID> findAllIDs();
}
