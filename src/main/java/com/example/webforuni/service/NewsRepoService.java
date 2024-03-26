package com.example.webforuni.service;

import com.example.webforuni.repository.NewsRepository;
import com.example.webforuni.user.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsRepoService implements NewsService {

    @Autowired
    NewsRepository repository;

    @Override
    public void create(News news) {
        repository.save(news);
    }

    @Override
    public List<News> readAll() {
        return repository.findAll();
    }
}
