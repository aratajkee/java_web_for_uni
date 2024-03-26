package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.UserService;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChangeUserController {

    @Autowired
    UserService userService;

    private static Integer currentId = null;

    @GetMapping("/changeUser/{id}")
    public String open(@PathVariable int id, Model model) {
        currentId = id;
        User user = userService.read(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", User.Role.getValues());
        return "change_user_form";
    }

    @PostMapping("/changeUser/update")
    public String updateUser(@ModelAttribute(name="user")User user, Model model) {
        String message = "";
        if (userService.update(user, currentId)) {
            message = "User updated!";
        } else {
            message = "User update failed!";
        }
        model.addAttribute("message", message);
        return "change_user_form";
    }
}
