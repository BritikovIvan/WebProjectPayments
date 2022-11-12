package com.webproject.service.impl;

import com.webproject.controller.dto.BankAccountDto;
import com.webproject.controller.dto.UserDto;
import com.webproject.mapper.BankAccountMapper;
import com.webproject.mapper.UserMapper;
import com.webproject.model.repository.BankAccountRepository;
import com.webproject.service.BankAccountService;
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
