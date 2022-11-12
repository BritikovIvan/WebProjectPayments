package com.webproject.controller;

import com.webproject.controller.dto.UserDto;
import com.webproject.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;

    @GetMapping("/main")
    public String getUserCards(Model model, HttpSession session) {
        var user = (UserDto) session.getAttribute("user");
        var userCards = creditCardService.getValidUserCards(user);
        model.addAttribute("userCards", userCards);
        return "customer_main";
    }

    @GetMapping("/credit_card/{id}")
    public String getCard(@PathVariable Long id, Model model) {
        var creditCard = creditCardService.findCardById(id);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }

    @PostMapping("/credit_card/{id}")
    public String blockCard(@PathVariable Long id, Model model) {
        var creditCard = creditCardService.blockCard(id);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }
}
