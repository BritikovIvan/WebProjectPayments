package com.web_project.mapper;

import com.web_project.controller.dto.UserRoleDto;
import com.web_project.model.entity.UserRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDto roleToRoleDto(UserRole userRole);
    UserRole roleDtoToRole(UserRoleDto roleDto);
}
