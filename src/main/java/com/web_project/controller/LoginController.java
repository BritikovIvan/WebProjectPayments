package com.web_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/login/access-denied")
    public String showLoginWithError(Model model) {
        model.addAttribute("exception", "Неправильное имя пользователя или пароль.");
        return "login";
    }
}
