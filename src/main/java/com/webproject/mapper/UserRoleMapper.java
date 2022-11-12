package com.webproject.mapper;

import com.webproject.controller.dto.UserRoleDto;
import com.webproject.model.entity.UserRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDto roleToRoleDto(UserRole userRole);
    UserRole roleDtoToRole(UserRoleDto roleDto);
}
