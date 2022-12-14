package com.web_project.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class PaymentDto {
    private LocalDateTime date;
    private String name;
    private BigDecimal amount;
    private UserDto sender;
    private BankAccountDto senderAccount;
    private CreditCardDto senderCard;
    private UserDto recipient;
    private BankAccountDto recipientAccount;
    private CreditCardDto recipientCard;
}
