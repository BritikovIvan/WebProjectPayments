package com.web_project.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LoginDto {
    private static final String passwordRegexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,20}$";

    @NotEmpty(message = "Login must not be blank")
    private String login;
    @NotEmpty
    @Pattern(regexp = passwordRegexp, message = "Password must have at least one digit and at least one letter")
    private String password;
}
