package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.EmailValidator;
import com.example.webforuni.service.UserService;
import com.example.webforuni.user.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateAccountController {

    @Autowired
    UserService userService;

    @Autowired
    EmailValidator emailValidator;

    @ModelAttribute(name = "user")
    public User newUser() {
        return new User();
    }

    @GetMapping("/createAccount")
    public String open() {
        return "create_account";
    }

    @PostMapping(value = "/createAccount/new")
    public String createAccount(@ModelAttribute(name = "user") @Valid User user, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("message","Неверный формат данных пользователя!");
            return "create_account";
        }
        user.setRole(User.Role.CLIENT);
        userService.create(user);
        model.addAttribute("message","Аккаунт успешно создан!");
        return "create_account";

    }

}
