package com.webproject.mapper;

import com.webproject.controller.dto.PaymentDto;
import com.webproject.model.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PaymentMapper {
    @Mapping(target = "sender", source = "payment.sender")
    @Mapping(target = "recipient", source = "payment.recipient")
    PaymentDto paymentToPaymentDto(Payment payment);
    @Mapping(target = "sender", source = "paymentDto.sender")
    @Mapping(target = "recipient", source = "paymentDto.recipient")
    Payment paymentDtoToPayment(PaymentDto paymentDto);
}
