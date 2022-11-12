package com.webproject.controller;

import com.webproject.controller.dto.UserDto;
import com.webproject.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final CreditCardService creditCardService;

    @GetMapping("/payment")
    public String getPaymentForm(HttpSession session, Model model) {
        var user = (UserDto) session.getAttribute("user");
        var userCards = creditCardService.getValidUserCards(user);
        model.addAttribute("userCards", userCards);
        return "payment";
    }
}
