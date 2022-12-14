package com.web_project.mapper;

import com.web_project.controller.dto.CreatePaymentDto;
import com.web_project.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring", uses = {UserMapper.class, BankAccountMapper.class, CreditCardMapper.class})
public interface CreatePaymentMapper {
    @Mapping(target = "senderCardId", source = "payment.senderCard.id")
    @Mapping(target = "recipientCardNumber", source = "payment.recipientCard.number")
    CreatePaymentDto paymentToPaymentDto(Payment payment);
    @Mapping(target = "senderCard.id", source = "paymentDto.senderCardId")
    @Mapping(target = "recipientCard.number", source = "paymentDto.recipientCardNumber")
    Payment paymentDtoToPayment(CreatePaymentDto paymentDto);
}
