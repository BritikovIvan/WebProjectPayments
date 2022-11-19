package com.web_project.service.impl;

import com.web_project.controller.dto.CreditCardDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.exception.UserNotFoundException;
import com.web_project.mapper.CreditCardMapper;
import com.web_project.model.entity.enums.CreditCardStatus;
import com.web_project.model.repository.CreditCardRepository;
import com.web_project.model.repository.UserRepository;
import com.web_project.service.CreditCardService;
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
        return changeCardStatus(id, CreditCardStatus.BLOCKED);
    }

    @Override
    public CreditCardDto unlockCard(Long id) {
        return changeCardStatus(id, CreditCardStatus.ACTIVE);
    }

    private CreditCardDto changeCardStatus(Long cardId, CreditCardStatus status) {
        var card = creditCardRepository.findById(cardId);
        if (card.isEmpty()) {
            throw new RuntimeException();
        }
        card.get().setStatus(status);
        var changedCard = creditCardRepository.save(card.get());
        return creditCardMapper.cardToCardDto(changedCard);
    }
}
