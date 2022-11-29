package com.web_project.service.impl;

import com.web_project.controller.dto.CreditCardDto;
import com.web_project.model.entity.BankAccount;
import com.web_project.model.entity.CreditCard;
import com.web_project.model.entity.User;
import com.web_project.model.entity.enums.CreditCardStatus;
import com.web_project.model.repository.CreditCardRepository;
import com.web_project.model.repository.UserRepository;
import com.web_project.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CreditCardServiceImplTest {
    private static final Long ID = 1L;
    @Autowired
    private CreditCardService creditCardService;
    @MockBean
    private CreditCardRepository creditCardRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    void givenCardId_whenGetCardById_thenReturnCardDto() {
        var card = new CreditCard();
        card.setId(ID);
        Mockito.doReturn(Optional.of(card)).when(creditCardRepository).findById(ID);
        var expected = new CreditCardDto();
        expected.setId(ID);

        var actual = creditCardService.findCardById(ID);

        assertEquals(expected, actual);
    }

    @Test
    void givenUserId_whenGetValidUserCards_thenReturnValidCardDtoList() {
        var creditCard = new CreditCard();
        creditCard.setId(ID);
        Mockito.doReturn(Collections.singletonList(creditCard)).when(creditCardRepository).findValidUserCards(ID);
        var creditCardDto = new CreditCardDto();
        creditCardDto.setId(ID);
        var expected = Collections.singletonList(creditCardDto);

        var actual = creditCardService.getValidUserCards(ID);

        assertEquals(expected, actual);
    }

    @Test
    void givenUserId_whenGetUserCards_thenReturnCardDtoList() {
        var creditCard = new CreditCard();
        creditCard.setId(ID);
        var bankAccount = new BankAccount();
        bankAccount.setCreditCards(Collections.singletonList(creditCard));
        var user = new User();
        user.setBankAccounts(Collections.singletonList(bankAccount));
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(ID);
        var creditCardDto = new CreditCardDto();
        creditCardDto.setId(ID);
        var expected = Collections.singletonList(creditCardDto);

        var actual = creditCardService.getUserCards(ID);

        assertEquals(expected, actual);
    }

    @Test
    void givenCardID_whenBlockCard_thenReturnBlockedCardDto() {
        var creditCard = new CreditCard();
        creditCard.setId(ID);
        Mockito.doReturn(Optional.of(creditCard)).when(creditCardRepository).findById(ID);
        creditCard.setStatus(CreditCardStatus.BLOCKED);
        Mockito.doReturn(creditCard).when(creditCardRepository).save(creditCard);

        var actual = creditCardService.blockCard(ID);

        assertEquals(ID, actual.getId());
        assertEquals(CreditCardStatus.BLOCKED, actual.getStatus());
    }

    @Test
    void givenCardId_whenUnlockCard_thenReturnUnlockCardDto() {
        var creditCard = new CreditCard();
        creditCard.setId(ID);
        Mockito.doReturn(Optional.of(creditCard)).when(creditCardRepository).findById(ID);
        creditCard.setStatus(CreditCardStatus.ACTIVE);
        Mockito.doReturn(creditCard).when(creditCardRepository).save(creditCard);

        var actual = creditCardService.unlockCard(ID);

        assertEquals(ID, actual.getId());
        assertEquals(CreditCardStatus.ACTIVE, actual.getStatus());
    }
}