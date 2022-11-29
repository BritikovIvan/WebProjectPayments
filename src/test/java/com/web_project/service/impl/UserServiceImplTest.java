package com.web_project.service.impl;

import com.web_project.controller.dto.UserDto;
import com.web_project.model.entity.User;
import com.web_project.model.repository.UserRepository;
import com.web_project.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTest {
    private static final Long ID = 1L;
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Test
    void givenUserId_whenFindById_thenReturnUserDto() {
        var user = new User();
        user.setId(ID);
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(ID);
        var expected = new UserDto();
        expected.setId(ID);

        var actual = userService.findById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void givenAdminId_whenFindAdminUsers_thenReturnUserDtoList() {
        var users = new ArrayList<User>();
        var user = new User();
        user.setId(ID);
        users.add(user);
        user = new User();
        user.setId(ID + 1);
        users.add(user);
        Mockito.doReturn(users).when(userRepository).findAll();
        var userDto = new UserDto();
        userDto.setId(ID + 1);
        var expected = Collections.singletonList(userDto);

        var actual = userService.findAdminUsers(ID);

        assertEquals(expected, actual);
    }
}