package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.EmailValidator;
import com.example.webforuni.service.UserService;
import com.example.webforuni.user.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChangeUserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailValidator emailValidator;

    private static Integer currentId = null;

    @ModelAttribute(name="roles")
    public List<String> getRoles() {
        return User.Role.getValues();
    }

    @GetMapping("/changeUser/{id}")
    public String open(@PathVariable int id, Model model) {
        currentId = id;
        model.addAttribute("user", userService.read(id));
        model.addAttribute("roles", User.Role.getValues());
        return "change_user_form";
    }

    @PostMapping("/changeUser/update")
    public String updateUser(@ModelAttribute(name="user") @Valid User user, Errors errors, Model model) {
        String message = "";
        if (errors.hasErrors()) {
            model.addAttribute("message", "Ошибка в формате данных пользователя!");
            return "change_user_form";
        }
        if (userService.update(user, currentId)) {
            message = "User updated!";
        } else {
            message = "User update failed!";
        }
        model.addAttribute("message", message);
        return "change_user_form";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/userPage";
    }
}
