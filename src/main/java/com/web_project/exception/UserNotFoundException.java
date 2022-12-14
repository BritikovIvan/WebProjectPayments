package com.web_project.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException() { }

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
