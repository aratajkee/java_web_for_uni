package com.example.webforuni.controllers.page_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}
