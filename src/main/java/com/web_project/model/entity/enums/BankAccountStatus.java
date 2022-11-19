package com.web_project.model.entity.enums;

public enum BankAccountStatus {
    ACTIVE("A"), BLOCKED("B");

    private final String code;

    BankAccountStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
