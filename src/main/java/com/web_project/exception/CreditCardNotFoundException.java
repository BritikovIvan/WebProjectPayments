package com.web_project.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreditCardNotFoundException extends RuntimeException {
    private String message;

    public CreditCardNotFoundException() { }

    public CreditCardNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
