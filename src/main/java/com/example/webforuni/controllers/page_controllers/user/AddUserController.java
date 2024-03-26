package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.UserService;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddUserController {

    @Autowired
    UserService userService;

    @GetMapping("/addUser")
    public String open(Model model) {
        model.addAttribute("roles", User.Role.getValues());
        return "add_user_form";
    }

    @PostMapping("/addUser/new")
    public String addUser(Model model, @ModelAttribute(name="message")String message, @ModelAttribute(name="user") User user) {
        message = "";
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
