package com.best.company.mappers;

import com.best.company.mappers.base.BaseDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.best.company.domain.address.Address;
import com.best.company.dto.address.AddressDTO;
import com.best.company.dto.address.AddressDetailDTO;

/**
 * User: Dawletbay Tilepbaev
 * Date: 20.07.2023 11:14
 */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends BaseDetailMapper<Address, AddressDTO, AddressDetailDTO> {}
