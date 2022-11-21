package com.web_project.service.impl;

import com.web_project.controller.dto.LoginDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.exception.UserNotFoundException;
import com.web_project.mapper.UserMapper;
import com.web_project.model.repository.LoginRepository;
import com.web_project.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LoginRepository repository;
    private final UserMapper mapper;

    private final Validator validator;

    @Override
    public UserDto findByLogin(LoginDto dto) {
        var violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            var stringBuilder = new StringBuilder();
            violations.forEach(violation -> stringBuilder.append(violation.getMessage()));
            throw new ConstraintViolationException("Error occurred: " + stringBuilder, violations);
        }

        var login = repository.findByLogin(dto.getLogin());
        if (login.isEmpty() || !login.get().getPassword().equals(dto.getPassword())) {
            throw new UserNotFoundException("Incorrect username or password.");
        }

        return mapper.userToUserDto(login.get().getUser());
    }
}
