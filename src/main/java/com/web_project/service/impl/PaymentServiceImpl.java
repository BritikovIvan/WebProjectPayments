package com.web_project.service.impl;

import com.web_project.controller.dto.CreatePaymentDto;
import com.web_project.controller.dto.PaymentDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.exception.CreditCardNotFoundException;
import com.web_project.exception.InsufficientFundsException;
import com.web_project.mapper.CreatePaymentMapper;
import com.web_project.mapper.PaymentMapper;
import com.web_project.model.entity.enums.CreditCardType;
import com.web_project.model.repository.CreditCardRepository;
import com.web_project.model.repository.PaymentsRepository;
import com.web_project.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
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
    private final Validator validator;

    @Override
    public List<PaymentDto> getUserPayments(UserDto user) {
        var payments = repository.findUserPayments(user.getId());
        return payments.stream().map(mapper::paymentToPaymentDto).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CreatePaymentDto makePayment(CreatePaymentDto paymentDto) {
        var violations = validator.validate(paymentDto);
        if (!violations.isEmpty()) {
            var stringBuilder = new StringBuilder();
            violations.forEach(violation -> stringBuilder.append(violation.getMessage()));
            throw new ConstraintViolationException("Error occurred: " + stringBuilder, violations);
        }

        var payment = createPaymentMapper.paymentDtoToPayment(paymentDto);
        var sender = creditCardRepository.findById(paymentDto.getSenderCardId());
        var recipient = creditCardRepository.findByNumber(payment.getRecipientCard().getNumber());
        if (sender.isEmpty() || recipient.isEmpty()) {
            throw new CreditCardNotFoundException("Credit card not found");
        }
        var senderCard = sender.get();
        var recipientCard = recipient.get();
        var senderBalance = senderCard.getBankAccount().getBalance();
        var recipientBalance = recipientCard.getBankAccount().getBalance();
        if (senderCard.getType().equals(CreditCardType.DEBIT) && senderBalance.compareTo(payment.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
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
