package com.web_project.service;

import com.web_project.controller.dto.CreditCardDto;
import com.web_project.controller.dto.UserDto;

import java.util.List;

public interface CreditCardService {
    CreditCardDto findCardById(Long id);

    List<CreditCardDto> getValidUserCards(Long userId);

    List<CreditCardDto> getActiveUserCards(Long userId);

    List<CreditCardDto> getUserCards(Long userId);

    CreditCardDto blockCard(Long id);

    CreditCardDto unlockCard(Long id);
}
