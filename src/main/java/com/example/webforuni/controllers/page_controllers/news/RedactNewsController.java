package com.example.webforuni.controllers.page_controllers.news;

import com.example.webforuni.service.NewsService;
import com.example.webforuni.user.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RedactNewsController {

    @Autowired
    NewsService newsService;
    @GetMapping("/redactNews/{id}")
    public String openRedactNewsForm(@PathVariable int id, Model model) {
        model.addAttribute("news", newsService.read(id));
        return "redact_news_form";
    }

    @PostMapping("/redactNews/redact/{id}")
    public String redactNews(@PathVariable int id, @ModelAttribute(name="news") News news) {
        newsService.update(news, id);
        return "redirect:/basePage";
    }

}
