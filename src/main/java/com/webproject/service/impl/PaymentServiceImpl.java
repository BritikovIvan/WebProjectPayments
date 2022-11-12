package com.webproject.service.impl;

import com.webproject.controller.dto.PaymentDto;
import com.webproject.controller.dto.UserDto;
import com.webproject.mapper.PaymentMapper;
import com.webproject.model.repository.PaymentsRepository;
import com.webproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentsRepository repository;
    private final PaymentMapper mapper;

    @Override
    public List<PaymentDto> getUserPayments(UserDto user) {
        var payments = repository.findUserPayments(user.getId());
        return payments.stream().map(mapper::paymentToPaymentDto).collect(Collectors.toList());
    }
}
