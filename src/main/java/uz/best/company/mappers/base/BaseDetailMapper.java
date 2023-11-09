package uz.best.company.mappers.base;

public interface BaseDetailMapper<Entity, DTO, DetailDTO> extends BaseMapper<Entity, DTO> {

    DetailDTO toDetailDTO(Entity entity);
}
