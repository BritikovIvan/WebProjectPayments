package com.web_project.service.impl;

import com.web_project.controller.dto.LoginDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.exception.UserNotFoundException;
import com.web_project.exception.WrongPasswordException;
import com.web_project.mapper.UserMapper;
import com.web_project.model.repository.LoginRepository;
import com.web_project.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LoginRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto findByLogin(LoginDto dto) {
        var login = repository.findByLogin(dto.getLogin());

        if (login.isEmpty()) {
            throw new UserNotFoundException();
        } else if (!login.get().getPassword().equals(dto.getPassword())) {
            throw new WrongPasswordException();
        }

        return mapper.userToUserDto(login.get().getUser());
    }
}
