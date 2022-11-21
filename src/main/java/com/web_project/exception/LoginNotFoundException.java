package com.web_project.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginNotFoundException extends RuntimeException {
    private String message;

    public LoginNotFoundException() { }

    public LoginNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
