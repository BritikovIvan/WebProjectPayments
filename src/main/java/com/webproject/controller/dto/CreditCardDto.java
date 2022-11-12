package com.webproject.controller.dto;

import com.webproject.model.entity.enums.CreditCardStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreditCardDto {
    private Long id;
    private String number;
    private String name;
    private CreditCardStatus status;
    private LocalDate validity;
    private BankAccountDto bankAccount;
}