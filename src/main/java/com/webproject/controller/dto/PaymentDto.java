package com.webproject.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto {
    private LocalDateTime date;
    private String name;
    private BigDecimal amount;
    private BankAccountDto sender;
    private BankAccountDto recipient;
}
