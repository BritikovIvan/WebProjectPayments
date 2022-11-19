package com.web_project.mapper;

import com.web_project.controller.dto.LoginDto;
import com.web_project.model.entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginDto loginToLoginDto(Login login);
    Login loginDtoToLogin(LoginDto dto);
}
