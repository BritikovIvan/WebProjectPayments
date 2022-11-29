package com.web_project.controller.dto;

import com.web_project.model.entity.enums.CreditCardStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class CreditCardDto {
    private Long id;
    private String number;
    private String name;
    private CreditCardStatus status;
    private LocalDate validity;
    private BankAccountDto bankAccount;
}
