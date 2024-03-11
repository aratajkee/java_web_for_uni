package com.example.webforuni.service;

import com.example.webforuni.repository.UserRepository;
import com.example.webforuni.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserRepoService implements UserService{

    private final UserRepository repository;

    public UserRepoService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public void create(User user, MultipartFile file) {
        try{
            user.setImageData(file.getBytes());
            repository.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Can`t save new user entity. Error: " + e);
        }
    }
    @Override
    public List<User> readAll() {
        return repository.findAll();
    }

    @Override
    public User read(int id) {
        return repository.findById(id).isPresent()
                ? repository.findById(id).get()
                : null;
    }

    @Override
    public boolean delete(int id) {
        repository.delete(repository.findById(id).get());
        return repository.findById(id).isEmpty();
    }

    @Override
    public boolean update(User user, int id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findById(id).get());
            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAvatar(int userId, MultipartFile file) {
        if (repository.findById(userId).isPresent()) {
            User user = repository.findById(userId).get();
            delete(userId);
            create(user, file);
            return true;
        }
        return false;
    }
}
