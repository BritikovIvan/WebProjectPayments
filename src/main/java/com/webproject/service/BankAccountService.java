package com.webproject.service;

import com.webproject.controller.dto.BankAccountDto;
import com.webproject.controller.dto.UserDto;

import java.util.List;

public interface BankAccountService {
    List<BankAccountDto> getUserAccounts(UserDto user);
}
