package com.webproject.service.impl;

import com.webproject.controller.dto.CreditCardDto;
import com.webproject.controller.dto.UserDto;
import com.webproject.exception.UserNotFoundException;
import com.webproject.mapper.CreditCardMapper;
import com.webproject.model.entity.enums.CreditCardStatus;
import com.webproject.model.repository.CreditCardRepository;
import com.webproject.model.repository.UserRepository;
import com.webproject.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;
    private final CreditCardMapper creditCardMapper;

    @Override
    public CreditCardDto findCardById(Long id) {
        var card = creditCardRepository.findById(id);
        if (card.isEmpty()) {
            throw new RuntimeException();
        }
        return creditCardMapper.cardToCardDto(card.get());
    }

    @Override
    public List<CreditCardDto> getValidUserCards(UserDto userDto) {
        var creditCards = creditCardRepository.findValidUserCards(userDto.getId());
        return creditCards.stream().map(creditCardMapper::cardToCardDto).collect(Collectors.toList());
    }

    @Override
    public List<CreditCardDto> getUserCards(UserDto userDto) {
        var user = userRepository.findById(userDto.getId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        var creditCards = user.get().getBankAccounts().stream()
                .flatMap(list -> list.getCreditCards().stream()).collect(Collectors.toList());
        return creditCards.stream().map(creditCardMapper::cardToCardDto).collect(Collectors.toList());
    }

    @Override
    public CreditCardDto blockCard(Long id) {
        var card = creditCardRepository.findById(id);
        if (card.isEmpty()) {
            throw new RuntimeException();
        }
        card.get().setStatus(CreditCardStatus.BLOCKED);
        var changedCard = creditCardRepository.save(card.get());
        return creditCardMapper.cardToCardDto(changedCard);
    }
}
