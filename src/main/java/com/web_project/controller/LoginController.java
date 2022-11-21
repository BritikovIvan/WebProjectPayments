package com.web_project.controller;

import com.web_project.controller.dto.LoginDto;
import com.web_project.exception.UserNotFoundException;
import com.web_project.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

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
        model.addAttribute("loginClass", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginClass") LoginDto dto, HttpSession session) {
        var user = service.findByLogin(dto);
        session.setAttribute("user", user);
        if (user.getRole().getRole().equals("ADMIN")) {
            return "redirect:admin_main";
        }
        return "redirect:main";
    }

    @ExceptionHandler({UserNotFoundException.class, ConstraintViolationException.class})
    public String handleLoginException(Model model, Exception exception) {
        model.addAttribute("exception", exception.getMessage());
        model.addAttribute("loginClass", new LoginDto());
        return "login";
    }
}
