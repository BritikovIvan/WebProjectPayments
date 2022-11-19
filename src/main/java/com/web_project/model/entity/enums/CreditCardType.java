package com.web_project.model.entity.enums;

public enum CreditCardType {
    DEBIT("D"), CREDIT("C"), OVERDRAFT("O");

    private final String code;

    CreditCardType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
