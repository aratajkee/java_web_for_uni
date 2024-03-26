package com.example.webforuni.service;

import com.example.webforuni.user.model.News;

import java.util.List;

public interface NewsService {
    void create(News news);
    List<News> readAll();

}
