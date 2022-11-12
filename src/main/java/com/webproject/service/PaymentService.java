package com.webproject.service;

import com.webproject.controller.dto.PaymentDto;
import com.webproject.controller.dto.UserDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getUserPayments(UserDto user);
}
