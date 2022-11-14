package com.webproject.service.impl;

import com.webproject.controller.dto.CreatePaymentDto;
import com.webproject.controller.dto.PaymentDto;
import com.webproject.controller.dto.UserDto;
import com.webproject.mapper.CreatePaymentMapper;
import com.webproject.mapper.PaymentMapper;
import com.webproject.model.entity.BankAccount;
import com.webproject.model.repository.CreditCardRepository;
import com.webproject.model.repository.PaymentsRepository;
import com.webproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentsRepository repository;
    private final CreditCardRepository creditCardRepository;
    private final PaymentMapper mapper;
    private final CreatePaymentMapper createPaymentMapper;

    @Override
    public List<PaymentDto> getUserPayments(UserDto user) {
        var payments = repository.findUserPayments(user.getId());
        return payments.stream().map(mapper::paymentToPaymentDto).collect(Collectors.toList());
    }


    @Override
    public CreatePaymentDto makePayment(CreatePaymentDto paymentDto) {
        var payment = createPaymentMapper.paymentDtoToPayment(paymentDto);
        payment.setDate(LocalDateTime.now());
        payment.setName("test payment");
        var recipientCard = creditCardRepository.findByNumber(payment.getRecipientCard().getNumber());
        if (recipientCard.isEmpty()) {
            throw new RuntimeException();
        }
        payment.setRecipientCard(recipientCard.get());
        payment.setRecipient(recipientCard.get().getBankAccount());
        var savePayment = repository.save(payment);
        return createPaymentMapper.paymentToPaymentDto(savePayment);
    }
}
