package com.web_project.controller;

import com.web_project.exception.UserNotFoundException;
import com.web_project.service.BankAccountService;
import com.web_project.service.CreditCardService;
import com.web_project.service.PaymentService;
import com.web_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
public class AdminController {
    private final UserService userService;
    private final CreditCardService creditCardService;
    private final BankAccountService accountService;
    private final PaymentService paymentService;

    @GetMapping("/admin_main")
    public String getUsers(Model model, Principal principal) {
        var users = userService.findAdminUsers(Long.parseLong(principal.getName()));
        model.addAttribute("users", users);
        return "admin_main";
    }

    @GetMapping("/user/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        var user = userService.findById(id);
        var cards = creditCardService.getUserCards(user.getId());
        var bankAccounts = accountService.getUserAccounts(user);
        var payments = paymentService.getUserPayments(user);
        model.addAttribute("user", user);
        model.addAttribute("cards", cards);
        model.addAttribute("bankAccounts", bankAccounts);
        model.addAttribute("payments", payments);
        return "user_details";
    }

    @ExceptionHandler({UserNotFoundException.class})
    public String handleUserNotFoundException(Model model, Exception exception) {
        model.addAttribute("exception", exception.getMessage());
        return "exception";
    }
}
