package com.web_project.service;

import com.web_project.controller.dto.BankAccountDto;
import com.web_project.controller.dto.UserDto;

import java.util.List;

public interface BankAccountService {
    List<BankAccountDto> getUserAccounts(UserDto user);
}
