package com.webproject.controller;

import com.webproject.model.entity.User;
import com.webproject.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;

    @GetMapping("/main")
    public String getUserCards(@ModelAttribute User user, Model model) {
        var userCards = user.getBankAccounts().stream()
                .flatMap(list -> list.getCreditCards().stream()).collect(Collectors.toList());
        model.addAttribute("userCards", userCards);
        return "main";
    }

    @GetMapping("/credit_card")
    public String getCard(@RequestParam(value = "id") Long id, Model model) {
        var creditCard = creditCardService.findCardById(id);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }
}
