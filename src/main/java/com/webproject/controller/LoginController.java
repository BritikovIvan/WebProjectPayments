package com.webproject.controller;

import com.webproject.model.entity.Login;
import com.webproject.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService service;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginClass", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginClass") Login login, RedirectAttributes redirectAttributes) {
        var user = service.findByLogin(login.getLogin(), login.getPassword());
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:main";
    }
}
