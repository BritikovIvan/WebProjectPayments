package com.web_project.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Setter
@Getter
public class CreatePaymentDto {
    private static final String creditCardRegexp = "[0-9]{16}";

    private BigDecimal amount;
    private Long senderCardId;
    @Pattern(regexp = creditCardRegexp, message = "Card number must have 16 digits")
    private String recipientCardNumber;
}
