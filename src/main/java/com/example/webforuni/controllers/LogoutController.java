package com.example.webforuni.controllers;

import com.example.webforuni.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @Autowired
    LoginService loginService;

    @GetMapping("/logOut")
    public String logout(){
        loginService.logOut();
        return "redirect:/login";
    }
}
