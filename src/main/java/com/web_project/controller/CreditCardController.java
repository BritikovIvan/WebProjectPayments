package com.web_project.controller;

import com.web_project.controller.dto.UserDto;
import com.web_project.service.CreditCardService;
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
    public String getCard(@PathVariable Long id, Model model, HttpSession session) {
        var creditCard = creditCardService.findCardById(id);
        var user = (UserDto) session.getAttribute("user");
        model.addAttribute("creditCard", creditCard);
        model.addAttribute("user", user);
        return "credit_card";
    }

    @PostMapping(value = "/credit_card/{id}", params = "block")
    public String blockCard(@PathVariable Long id, Model model, HttpSession session) {
        var creditCard = creditCardService.blockCard(id);
        var user = (UserDto) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }

    @PostMapping(value = "/credit_card/{id}", params = "unlock")
    public String unlockCard(@PathVariable Long id, Model model, HttpSession session) {
        var creditCard = creditCardService.unlockCard(id);
        var user = (UserDto) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("creditCard", creditCard);
        return "credit_card";
    }
}
