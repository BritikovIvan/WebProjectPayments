package com.web_project.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreatePaymentDto {
    private BigDecimal amount;
    private Long senderCardId;
    private String recipientCardNumber;
}
