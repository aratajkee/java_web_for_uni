package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.*;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userPage")
public class UserPageController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @GetMapping
    public String open(Model model) {
        return "userPage";
    }

    @ModelAttribute(name = "currentUser")
    public User user() {
        return loginService.getCurrentUser();
    }

    @ModelAttribute(name="users")
    public List<User> users() {
        if (loginService.getCurrentUser() != null) {
            return userService.readAll();
        }
        return null;
    }

    @ModelAttribute(name = "image")
    public String getAvatar() {
        if (loginService.getCurrentUser() != null) {
            return loginService.getCurrentUser().generateBase64Image();
        }
        return null;
    }

}
