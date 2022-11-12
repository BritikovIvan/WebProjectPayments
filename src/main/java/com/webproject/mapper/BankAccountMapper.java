package com.webproject.mapper;

import com.webproject.controller.dto.BankAccountDto;
import com.webproject.model.entity.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    BankAccountDto accountToAccountDto(BankAccount bankAccount);
    BankAccount accountDtoToAccount(BankAccountDto accountDto);
}
