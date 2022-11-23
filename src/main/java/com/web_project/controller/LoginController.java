package com.web_project.controller;

import com.web_project.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolationException;
import java.security.Principal;

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

//    @ExceptionHandler()
//    public String handleLoginException(Model model, Exception exception) {
//        model.addAttribute("exception", exception.getMessage());
//        return "login";
//    }
}
