package com.web_project.service.impl;

import com.web_project.controller.dto.UserDto;
import com.web_project.exception.UserNotFoundException;
import com.web_project.mapper.UserMapper;
import com.web_project.model.repository.UserRepository;
import com.web_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    @Override
    public UserDto findById(Long id) {
        var user = repository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return mapper.userToUserDto(user.get());
    }

    @Override
    public List<UserDto> findAdminUsers(UserDto admin) {
        var users = repository.findAll();
        users.removeIf(user -> user.getId().equals(admin.getId()));
        return users.stream().map(mapper::userToUserDto).collect(Collectors.toList());
    }
}
