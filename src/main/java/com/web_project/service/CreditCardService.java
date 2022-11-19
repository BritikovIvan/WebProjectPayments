package com.web_project.service;

import com.web_project.controller.dto.CreditCardDto;
import com.web_project.controller.dto.UserDto;

import java.util.List;

public interface CreditCardService {
    CreditCardDto findCardById(Long id);

    List<CreditCardDto> getValidUserCards(UserDto userDto);

    List<CreditCardDto> getUserCards(UserDto userDto);

    CreditCardDto blockCard(Long id);

    CreditCardDto unlockCard(Long id);
}
