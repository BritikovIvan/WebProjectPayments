package com.web_project.mapper;

import com.web_project.controller.dto.CreditCardDto;
import com.web_project.model.entity.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BankAccountMapper.class})
public interface CreditCardMapper {
    @Mapping(target = "bankAccount", source = "card.bankAccount")
    CreditCardDto cardToCardDto(CreditCard card);
    @Mapping(target = "bankAccount", source = "cardDto.bankAccount")
    CreditCard cardDtoToCard(CreditCardDto cardDto);
}
