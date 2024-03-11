package com.example.webforuni.controllers;

import com.example.webforuni.service.LoginService;
import com.example.webforuni.service.TimeService;
import com.example.webforuni.user.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basePage")
public class MainPageController {
    private final TimeService timeService;
    private final LoginService loginService;

    @Autowired
    public MainPageController(TimeService timeService, LoginService loginService) {
        this.timeService = timeService;
        this.loginService = loginService;
    }

    @ModelAttribute(name = "dateTime")
    public String getTime() {
        return timeService.getCurrentTime();
    }

    @ModelAttribute(name = "loginCounter")
    public String getLoginCounter() {
        return loginService.getCounter().toString();
    }

    @GetMapping
    public String open() {
        return "basePage";
    }
}
