package com.web_project.controller.dto;

import com.web_project.model.entity.enums.BankAccountStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class BankAccountDto {
    private Long id;
    @NotEmpty
    @Pattern(regexp = "[A-Z]{2}[0-9]{2}[a-zA-Z0-9]{0,30}", message = "The total length of an IBAN cannot exceed 34 characters")
    private String iban;
    private BankAccountStatus status;
    private BigDecimal balance;
}
