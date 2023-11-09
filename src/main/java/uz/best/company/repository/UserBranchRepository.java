package uz.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import uz.best.company.domain.auth.UserBranch;

import java.util.UUID;

@Repository
public interface UserBranchRepository extends R2dbcRepository<UserBranch, UUID> {
}
