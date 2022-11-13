package com.webproject.controller;

import com.webproject.controller.dto.UserDto;
import com.webproject.service.BankAccountService;
import com.webproject.service.CreditCardService;
import com.webproject.service.PaymentService;
import com.webproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final CreditCardService creditCardService;
    private final BankAccountService accountService;
    private final PaymentService paymentService;

    @GetMapping("/admin_main")
    public String getUsers(Model model, HttpSession session) {
        var user = (UserDto) session.getAttribute("user");
        var users = userService.findAdminUsers(user);
        model.addAttribute("users", users);
        return "admin_main";
    }

    @GetMapping("/user/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        var user = userService.findById(id);
        var cards = creditCardService.getUserCards(user);
        var bankAccounts = accountService.getUserAccounts(user);
        var payments = paymentService.getUserPayments(user);
        model.addAttribute("user", user);
        model.addAttribute("cards", cards);
        model.addAttribute("bankAccounts", bankAccounts);
        model.addAttribute("payments", payments);
        return "user_details";
    }
}
