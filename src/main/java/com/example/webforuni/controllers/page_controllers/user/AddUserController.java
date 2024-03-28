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

import java.util.List;

@Controller
public class AddUserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailValidator emailValidator;

    @GetMapping("/addUser")
    public String open() {
        return "add_user_form";
    }

    @ModelAttribute(name="roles")
    public List<String> getRoles() {
        return User.Role.getValues();
    }

    @PostMapping("/addUser/new")
    public String addUser(Model model, @ModelAttribute(name="user") @Valid User user, Errors errors) {
        String message = "";
        if (errors.hasErrors()) {
            model.addAttribute("message", "Неверный формат данных пользователя!");
            return "add_user_form";
        }
        try{
            userService.create(user);
            message = "New user created!";
        } catch (Exception e) {
            message = "Error create new user: " + e.getMessage();
        }
        model.addAttribute("message", message);
        return "add_user_form";
    }

    @ModelAttribute(name = "user")
    public User getUser() {
        return new User();
    }
}
