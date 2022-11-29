package com.web_project.service.impl;

import com.web_project.controller.dto.CreatePaymentDto;
import com.web_project.controller.dto.PaymentDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.model.entity.CreditCard;
import com.web_project.model.entity.Payment;
import com.web_project.model.entity.User;
import com.web_project.model.repository.CreditCardRepository;
import com.web_project.model.repository.PaymentRepository;
import com.web_project.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PaymentServiceImplTest {
    private static final Long ID = 1L;
    private static final String CARD_NUMBER = "1111111111111111";
    @Autowired
    private PaymentService paymentService;
    @MockBean
    private PaymentRepository paymentRepository;
    @MockBean
    private CreditCardRepository cardRepository;

    @Test
    void givenUserDto_whenGetUserPayments_thenReturnPaymentDtoList() {
        var userDto = new UserDto();
        userDto.setId(ID);
        var user = new User();
        user.setId(ID);
        var payment = new Payment();
        payment.setName("test");
        Mockito.doReturn(Collections.singletonList(payment)).when(paymentRepository).findUserPayments(ID);
        var paymentDto = new PaymentDto();
        paymentDto.setName("test");
        var expected = Collections.singletonList(paymentDto);

        var actual = paymentService.getUserPayments(userDto);

        assertEquals(expected,actual);
    }
}