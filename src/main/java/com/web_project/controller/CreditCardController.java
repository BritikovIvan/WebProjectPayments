package com.web_project.controller;

import com.web_project.exception.CreditCardNotFoundException;
import com.web_project.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardService creditCardService;

    @Secured("ROLE_CLIENT")
    @GetMapping("/main")
    public String getUserCards(Model model, Principal principal) {
        var userCards = creditCardService.getValidUserCards(Long.parseLong(principal.getName()));
        model.addAttribute("userCards", userCards);
        return "customer_main";
    }

    @Secured({"ROLE_CLIENT", "ROLE_ADMIN"})
    @GetMapping("/credit_card/{id}")
    public String getCard(@PathVariable Long id, Model model) {
        var creditCard = creditCardService.findCardById(id);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }

    @Secured({"ROLE_CLIENT", "ROLE_ADMIN"})
    @PostMapping(value = "/credit_card/{id}", params = "block")
    public String blockCard(@PathVariable Long id, Model model) {
        var creditCard = creditCardService.blockCard(id);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(value = "/credit_card/{id}", params = "unlock")
    public String unlockCard(@PathVariable Long id, Model model) {
        var creditCard = creditCardService.unlockCard(id);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }

    @ExceptionHandler({CreditCardNotFoundException.class})
    public String handleCardNotFoundException(Model model, Exception exception) {
        model.addAttribute("exception", exception.getMessage());
        return "exception";
    }
}
