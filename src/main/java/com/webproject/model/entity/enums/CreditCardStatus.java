package com.webproject.model.entity.enums;

public enum CreditCardStatus {
    ACTIVE("A"), BLOCKED("B"), EXPIRED("E");

    private final String code;

    CreditCardStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
