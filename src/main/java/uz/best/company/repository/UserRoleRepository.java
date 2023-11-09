package uz.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.best.company.domain.auth.UserRole;

import java.util.UUID;

@Repository
public interface UserRoleRepository extends R2dbcRepository<UserRole, UUID> {

    Flux<UserRole> findAllByUserId(UUID userId);
}
