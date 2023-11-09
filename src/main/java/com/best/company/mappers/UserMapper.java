package com.best.company.mappers;

import com.best.company.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.best.company.domain.auth.User;
import com.best.company.dto.auth.UserDTO;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDTO> {}
