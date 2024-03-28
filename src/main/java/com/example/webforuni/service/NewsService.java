package com.example.webforuni.service;

import com.example.webforuni.user.model.News;

import java.util.List;

public interface NewsService {
    void create(News news);
    News read(int id);
    List<News> readAll();
    boolean delete(int id);
    boolean update(News news, int id);

}
