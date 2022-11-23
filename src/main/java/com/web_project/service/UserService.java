package com.web_project.service;

import com.web_project.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto findById(Long id);

    List<UserDto> findAdminUsers(Long adminId);
}
