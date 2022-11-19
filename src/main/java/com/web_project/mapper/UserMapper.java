package com.web_project.mapper;

import com.web_project.controller.dto.UserDto;
import com.web_project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserRoleMapper.class})
public interface UserMapper {
    @Mapping(target = "role", source = "user.role")
    UserDto userToUserDto(User user);
    @Mapping(target = "role", source = "dto.role")
    User userDtoToUser(UserDto dto);
}
