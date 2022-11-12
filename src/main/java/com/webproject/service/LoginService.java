package com.webproject.service;

import com.webproject.controller.dto.LoginDto;
import com.webproject.controller.dto.UserDto;

public interface LoginService {
    UserDto findByLogin(LoginDto dto);
}
