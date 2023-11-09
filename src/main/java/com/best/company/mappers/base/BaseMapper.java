package com.best.company.mappers.base;

import org.mapstruct.MappingTarget;

/**
 * User: Dawletbay Tilepbaev
 * Date: 20.07.2023 11:14
 */
public interface BaseMapper<Entity, DTO> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto);

    Entity merge(DTO dto, @MappingTarget Entity entity);
}
