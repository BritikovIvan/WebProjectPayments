package com.webproject.service.impl;

import com.webproject.controller.dto.LoginDto;
import com.webproject.controller.dto.UserDto;
import com.webproject.exception.UserNotFoundException;
import com.webproject.exception.WrongPasswordException;
import com.webproject.mapper.UserMapper;
import com.webproject.model.repository.LoginRepository;
import com.webproject.service.LoginService;
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
