package com.example.webforuni.service;

import com.example.webforuni.repository.NewsRepository;
import com.example.webforuni.user.model.News;
import com.example.webforuni.user.model.User;
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
    public News read(int id) {
        return repository.findById(id).isPresent()
                ? repository.findById(id).get()
                : null;
    }

    @Override
    public List<News> readAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(int id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findById(id).get());
            return true;
        }
        return false;
    }

    @Override
    public boolean update(News news, int id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findById(id).get());
            repository.save(news);
            return true;
        }
        return false;
    }
}
