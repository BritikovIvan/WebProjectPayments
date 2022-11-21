package com.web_project.controller;

import com.web_project.controller.dto.CreatePaymentDto;
import com.web_project.controller.dto.UserDto;
import com.web_project.exception.CreditCardNotFoundException;
import com.web_project.exception.InsufficientFundsException;
import com.web_project.service.CreditCardService;
import com.web_project.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final CreditCardService creditCardService;
    private final PaymentService paymentService;

    @GetMapping("/payment")
    public String getPaymentForm(HttpSession session, Model model) {
        var user = (UserDto) session.getAttribute("user");
        var userCards = creditCardService.getValidUserCards(user);
        model.addAttribute("userCards", userCards);
        model.addAttribute("payment", new CreatePaymentDto());
        return "payment";
    }

    @PostMapping("/payment")
    public String makePayment(@ModelAttribute("payment") CreatePaymentDto paymentDto) {
        paymentService.makePayment(paymentDto);
        return "redirect:main";
    }

    @ExceptionHandler({ConstraintViolationException.class, CreditCardNotFoundException.class})
    public String handleCreditCardException(Model model, Exception exception, HttpSession session) {
        model.addAttribute("creditCardException", exception.getMessage());
        var user = (UserDto) session.getAttribute("user");
        var userCards = creditCardService.getValidUserCards(user);
        model.addAttribute("userCards", userCards);
        model.addAttribute("payment", new CreatePaymentDto());
        return "payment";
    }

    @ExceptionHandler({InsufficientFundsException.class})
    public String handleAmountException(Model model, Exception exception, HttpSession session) {
        model.addAttribute("amountException", exception.getMessage());
        var user = (UserDto) session.getAttribute("user");
        var userCards = creditCardService.getValidUserCards(user);
        model.addAttribute("userCards", userCards);
        model.addAttribute("payment", new CreatePaymentDto());
        return "payment";
    }
}
