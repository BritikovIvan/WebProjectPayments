package com.web_project.service.impl;

import com.web_project.controller.dto.CreatePaymentDto;
import com.web_project.controller.dto.PaymentDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.mapper.CreatePaymentMapper;
import com.web_project.mapper.PaymentMapper;
import com.web_project.model.repository.CreditCardRepository;
import com.web_project.model.repository.PaymentsRepository;
import com.web_project.service.PaymentService;
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
    @Transactional
    public CreatePaymentDto makePayment(CreatePaymentDto paymentDto) {
        var payment = createPaymentMapper.paymentDtoToPayment(paymentDto);
        var senderCard = creditCardRepository.findById(paymentDto.getSenderCardId()).get();
        var recipientCard = creditCardRepository.findByNumber(payment.getRecipientCard().getNumber()).get();
        var senderBalance = senderCard.getBankAccount().getBalance();
        var recipientBalance = recipientCard.getBankAccount().getBalance();
        senderCard.getBankAccount().setBalance(senderBalance.subtract(payment.getAmount()));
        recipientCard.getBankAccount().setBalance(recipientBalance.add(payment.getAmount()));
        payment.setDate(LocalDateTime.now());
        payment.setName("retail mobile bank");
        payment.setSender(senderCard.getBankAccount());
        payment.setSenderCard(senderCard);
        payment.setRecipientCard(recipientCard);
        payment.setRecipient(recipientCard.getBankAccount());
        var savePayment = repository.save(payment);
        return createPaymentMapper.paymentToPaymentDto(savePayment);
    }
}
