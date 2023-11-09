package uz.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import uz.best.company.domain.address.Region;

import java.util.UUID;

@Repository
public interface RegionRepository extends R2dbcRepository<Region, UUID> {

    Mono<Region> findByCode(Long code);
}
