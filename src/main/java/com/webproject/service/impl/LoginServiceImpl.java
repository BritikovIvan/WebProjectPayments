package com.webproject.service.impl;

import com.webproject.exception.UserNotFoundException;
import com.webproject.exception.WrongPasswordException;
import com.webproject.model.entity.User;
import com.webproject.model.repository.LoginRepository;
import com.webproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LoginRepository repository;

    @Override
    public User findByLogin(String login, String password) {
        var user = repository.findByLogin(login);

        if (user == null) {
            throw new UserNotFoundException();
        } else if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException();
        }

        return user.getUser();
    }
}
