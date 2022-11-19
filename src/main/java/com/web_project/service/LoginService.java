package com.web_project.service;

import com.web_project.controller.dto.LoginDto;
import com.web_project.controller.dto.UserDto;

public interface LoginService {
    UserDto findByLogin(LoginDto dto);
}
