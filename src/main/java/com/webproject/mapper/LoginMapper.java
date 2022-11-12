package com.webproject.mapper;

import com.webproject.controller.dto.LoginDto;
import com.webproject.model.entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginDto loginToLoginDto(Login login);
    Login loginDtoToLogin(LoginDto dto);
}
