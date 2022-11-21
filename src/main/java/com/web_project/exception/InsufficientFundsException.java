package com.web_project.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsufficientFundsException extends RuntimeException {
    private String message;

    public InsufficientFundsException() { }

    public InsufficientFundsException(String message) {
        super(message);
        this.message = message;
    }
}
