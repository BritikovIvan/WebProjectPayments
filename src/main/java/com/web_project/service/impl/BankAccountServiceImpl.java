package com.web_project.service.impl;

import com.web_project.controller.dto.BankAccountDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.mapper.BankAccountMapper;
import com.web_project.mapper.UserMapper;
import com.web_project.model.repository.BankAccountRepository;
import com.web_project.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository repository;
    private final BankAccountMapper accountMapper;
    private final UserMapper userMapper;

    @Override
    public List<BankAccountDto> getUserAccounts(UserDto user) {
        var accounts = repository.findByUser(userMapper.userDtoToUser(user));
        return accounts.stream().map(accountMapper::accountToAccountDto).collect(Collectors.toList());
    }
}
