package com.web_project.mapper;

import com.web_project.controller.dto.PaymentDto;
import com.web_project.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BankAccountMapper.class, CreditCardMapper.class})
public interface PaymentMapper {
    @Mapping(target = "sender", source = "payment.sender.user")
    @Mapping(target = "recipient", source = "payment.recipient.user")
    @Mapping(target = "senderAccount", source = "payment.sender")
    @Mapping(target = "recipientAccount", source = "payment.sender")
    @Mapping(target = "senderCard", source = "payment.senderCard")
    @Mapping(target = "recipientCard", source = "payment.recipientCard")
    PaymentDto paymentToPaymentDto(Payment payment);
    @Mapping(target = "sender", source = "paymentDto.senderAccount")
    @Mapping(target = "recipient", source = "paymentDto.recipientAccount")
    @Mapping(target = "senderCard", source = "paymentDto.senderCard")
    @Mapping(target = "recipientCard", source = "paymentDto.recipientCard")
    Payment paymentDtoToPayment(PaymentDto paymentDto);
}
