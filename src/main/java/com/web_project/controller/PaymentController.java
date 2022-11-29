package com.web_project.controller;

import com.web_project.controller.dto.CreatePaymentDto;
import com.web_project.exception.CreditCardNotFoundException;
import com.web_project.exception.InsufficientFundsException;
import com.web_project.service.CreditCardService;
import com.web_project.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolationException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Secured("ROLE_CLIENT")
public class PaymentController {
    private final CreditCardService creditCardService;
    private final PaymentService paymentService;

    @GetMapping("/payment")
    public String getPaymentForm(Model model, Principal principal) {
        var userCards = creditCardService.getActiveUserCards(Long.parseLong(principal.getName()));
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
    public String handleCreditCardException(Model model, Exception exception, Principal principal) {
        model.addAttribute("creditCardException", exception.getMessage());
        var userCards = creditCardService.getValidUserCards(Long.parseLong(principal.getName()));
        model.addAttribute("userCards", userCards);
        model.addAttribute("payment", new CreatePaymentDto());
        return "payment";
    }

    @ExceptionHandler({InsufficientFundsException.class})
    public String handleAmountException(Model model, Exception exception, Principal principal) {
        model.addAttribute("amountException", exception.getMessage());
        var userCards = creditCardService.getValidUserCards(Long.parseLong(principal.getName()));
        model.addAttribute("userCards", userCards);
        model.addAttribute("payment", new CreatePaymentDto());
        return "payment";
    }
}
