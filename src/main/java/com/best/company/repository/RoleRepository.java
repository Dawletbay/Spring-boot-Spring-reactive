package com.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import com.best.company.domain.auth.Role;

import java.util.UUID;

@Repository
public interface RoleRepository extends R2dbcRepository<Role, UUID> {
}
