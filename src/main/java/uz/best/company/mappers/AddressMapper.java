package uz.best.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.best.company.mappers.base.BaseDetailMapper;
import uz.best.company.domain.address.Address;
import uz.best.company.dto.address.AddressDTO;
import uz.best.company.dto.address.AddressDetailDTO;

/**
 * User: Dawletbay Tilepbaev
 * Date: 20.07.2023 11:14
 */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper extends BaseDetailMapper<Address, AddressDTO, AddressDetailDTO> {}
