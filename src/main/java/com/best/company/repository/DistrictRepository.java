package com.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import com.best.company.domain.address.District;

import java.util.UUID;

@Repository
public interface DistrictRepository extends R2dbcRepository<District, UUID> {
    Mono<District> findByDistrictId(String code);

    Mono<District> findByRegionCodeAndCode(Long regionCode, Long code);
}
