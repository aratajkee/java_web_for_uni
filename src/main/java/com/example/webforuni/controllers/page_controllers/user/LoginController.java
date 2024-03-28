package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.LoginService;
import com.example.webforuni.user.model.User;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @GetMapping
    public String openLoginPage() {
        return "loginForm";
    }

    @GetMapping("/askLogin")
    public String askLogin(@Valid User user, Errors errors, Model model) {
        String message = "";
        if (errors.hasErrors()) {
            message = "Неверный формат данных пользователя!";
            model.addAttribute("message", message);
            return "loginForm";
        }
        if (loginService.askLogin(user)) {
            return "redirect:/basePage";
        }
        message = "Такого пользователя не существует!";
        model.addAttribute("message", message);
        return "loginForm";
    }
}
