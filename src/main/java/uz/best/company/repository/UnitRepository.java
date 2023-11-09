package uz.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import uz.best.company.domain.Unit;

import java.util.UUID;
/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 01.08.2023 17:30
 */
@Repository
public interface UnitRepository extends R2dbcRepository<Unit, UUID> {

    Mono<Unit> findByMeasureId(Long measureId);
}
