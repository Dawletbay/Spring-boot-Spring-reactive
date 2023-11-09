package com.best.company.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import com.best.company.domain.address.Address;

import java.util.UUID;

@Repository
public interface AddressRepository extends R2dbcRepository<Address, UUID> {
}
