package uz.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import uz.best.company.domain.ActivityType;

import java.util.UUID;

@Repository
public interface ActivityTypeRepository extends R2dbcRepository<ActivityType, UUID> {
}
