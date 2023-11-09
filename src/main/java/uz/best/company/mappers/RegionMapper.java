package uz.best.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.best.company.mappers.base.BaseMapper;
import uz.best.company.domain.address.Region;
import uz.best.company.dto.address.RegionDTO;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 01.08.2023 10:54
 */

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RegionMapper extends BaseMapper<Region, RegionDTO> {
}
