package com.web_project.service.impl;

import com.web_project.controller.dto.BankAccountDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.model.entity.BankAccount;
import com.web_project.model.entity.User;
import com.web_project.model.repository.BankAccountRepository;
import com.web_project.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BankAccountServiceImplTest {
    private static final Long ID = 1L;
    @Autowired
    private BankAccountService bankAccountService;
    @MockBean
    private BankAccountRepository bankAccountRepository;

    @Test
    void givenUserDto_whenGetUserAccounts_thenReturnUserAccountsDto() {
        var userDto = new UserDto();
        userDto.setId(ID);
        var user = new User();
        user.setId(ID);
        var bankAccount = new BankAccount();
        bankAccount.setId(ID);
        Mockito.doReturn(Collections.singletonList(bankAccount)).when(bankAccountRepository).findByUser(user);
        var bankAccountDto = new BankAccountDto();
        bankAccountDto.setId(ID);
        var expected = Collections.singletonList(bankAccountDto);

        var actual = bankAccountService.getUserAccounts(userDto);

        assertNotNull(actual);
        assertEquals(expected,actual);
    }
}