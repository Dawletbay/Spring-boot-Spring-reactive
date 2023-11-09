package com.best.company.mappers;

import com.best.company.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.best.company.domain.address.Region;
import com.best.company.dto.address.RegionDTO;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 01.08.2023 10:54
 */

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RegionMapper extends BaseMapper<Region, RegionDTO> {
}
