package com.webproject.service.impl;

import com.webproject.controller.dto.UserDto;
import com.webproject.exception.UserNotFoundException;
import com.webproject.mapper.UserMapper;
import com.webproject.model.repository.UserRepository;
import com.webproject.service.UserService;
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
            throw new UserNotFoundException();
        }
        return mapper.userToUserDto(user.get());
    }

    @Override
    public List<UserDto> findAll() {
        var users = repository.findAll();
        return users.stream().map(mapper::userToUserDto).collect(Collectors.toList());
    }
}
