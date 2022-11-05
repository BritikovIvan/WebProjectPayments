package com.webproject.service;

import com.webproject.model.entity.User;

public interface LoginService {
    User findByLogin(String login, String password);
}
