package com.example.webforuni.controllers;

import com.example.webforuni.service.*;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userPage")
public class UserPageController {

    @Autowired
    LoginService loginService;

    @GetMapping
    public String open(Model model) {
        return "userPage";
    }

    @ModelAttribute(name = "user")
    public User user() {
        return loginService.getCurrentUser();
    }

    @ModelAttribute(name = "image")
    public String getAvatar() {
        return loginService.getCurrentUser().generateBase64Image();
    }

}
