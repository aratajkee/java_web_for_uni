package com.example.webforuni.controllers.page_controllers.news;

import com.example.webforuni.service.LoginService;
import com.example.webforuni.service.NewsService;
import com.example.webforuni.service.TimeService;
import com.example.webforuni.user.model.News;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/basePage")
public class NewsPageController {
    private final TimeService timeService;
    private final LoginService loginService;

    private final NewsService newsService;

    @Autowired
    public NewsPageController(TimeService timeService, LoginService loginService, NewsService newsService) {
        this.timeService = timeService;
        this.loginService = loginService;
        this.newsService = newsService;
    }

    @ModelAttribute(name = "dateTime")
    public String getTime() {
        return timeService.getCurrentTime();
    }

    @ModelAttribute(name = "loginCounter")
    public String getLoginCounter() {
        return loginService.getCounter().toString();
    }

    @ModelAttribute(name="currentUserRole")
    public String getCurrentUserRole() {
        return loginService.getCurrentUser() != null
                ? loginService.getCurrentUser().getRole()
                : User.Role.CLIENT.toString();
    }

    @ModelAttribute(name="newsList")
    public List<News> getNews() {
        List<News> newsList = newsService.readAll();
        Collections.reverse(newsList);
        return newsList;
    }
    @GetMapping
    public String open() {
        return "news_page";
    }

    @GetMapping("/deleteNews/{id}")
    public String deleteNews(@PathVariable int id) {
        newsService.delete(id);
        return "redirect:/basePage";
    }
}
