package com.web_project.service;

import com.web_project.controller.dto.CreatePaymentDto;
import com.web_project.controller.dto.PaymentDto;
import com.web_project.controller.dto.UserDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getUserPayments(UserDto user);
    PaymentDto makePayment(CreatePaymentDto paymentDto);
}
