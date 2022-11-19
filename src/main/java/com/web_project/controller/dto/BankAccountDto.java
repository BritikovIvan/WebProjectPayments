package com.web_project.controller.dto;

import com.web_project.model.entity.enums.BankAccountStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankAccountDto {
    private Long id;
    private String iban;
    private BankAccountStatus status;
    private BigDecimal balance;
}
