package com.example.webforuni.controllers.page_controllers;

import com.example.webforuni.service.NewsService;
import com.example.webforuni.user.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateNewsController {

    @Autowired
    NewsService newsService;

    @GetMapping("/createNews")
    public String openCreateNewsForm() {
        return "create_news_form";
    }

    @ModelAttribute(name="news")
    public News createNewsObject() {
        return new News();
    }

    @PostMapping("/createNews/post")
    public String postNews(@ModelAttribute(name="news") News news) {
        newsService.create(news);
        return "redirect:/basePage";
    }
}
