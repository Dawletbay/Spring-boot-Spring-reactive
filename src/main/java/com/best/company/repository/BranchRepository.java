package com.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import com.best.company.domain.Branch;

import java.util.UUID;

@Repository
public interface BranchRepository extends R2dbcRepository<Branch, UUID> {
}
