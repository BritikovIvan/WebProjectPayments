package com.webproject.service;

import com.webproject.controller.dto.CreditCardDto;
import com.webproject.controller.dto.UserDto;

import java.util.List;

public interface CreditCardService {
    CreditCardDto findCardById(Long id);

    List<CreditCardDto> getValidUserCards(UserDto userDto);

    List<CreditCardDto> getUserCards(UserDto userDto);

    CreditCardDto blockCard(Long id);
}
