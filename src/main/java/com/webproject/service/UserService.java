package com.webproject.service;

import com.webproject.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findById(Long id);

    List<UserDto> findAdminUsers(UserDto admin);
}
