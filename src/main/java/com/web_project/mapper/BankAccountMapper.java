package com.web_project.mapper;

import com.web_project.controller.dto.BankAccountDto;
import com.web_project.model.entity.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    BankAccountDto accountToAccountDto(BankAccount bankAccount);
    BankAccount accountDtoToAccount(BankAccountDto accountDto);
}
