package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.EmailValidator;
import com.example.webforuni.service.UserService;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateAccountController {

    @Autowired
    UserService userService;

    @ModelAttribute(name="user")
    public User newUser() {
        return new User();
    }

    @GetMapping("/createAccount")
    public String open() {
        return "create_account";
    }

    @PostMapping(value = "/createAccount/new")
    public String createAccount(@ModelAttribute(name="user") User user) {
        if (EmailValidator.isValidEmail(user.getEmail())) {
            user.setRole(User.Role.CLIENT);
            userService.create(user);
            return "redirect:/login";
        }
        return "redirect:/createAccount";
    }

}
