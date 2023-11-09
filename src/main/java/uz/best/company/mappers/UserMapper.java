package uz.best.company.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import uz.best.company.mappers.base.BaseMapper;
import uz.best.company.domain.auth.User;
import uz.best.company.dto.auth.UserDTO;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDTO> {}
