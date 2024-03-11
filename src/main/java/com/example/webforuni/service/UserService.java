package com.example.webforuni.service;

import com.example.webforuni.user.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void create(User user);
    void create(User user, MultipartFile file);
    List<User> readAll();
    User read(int id);
    boolean delete(int id);
    boolean update(User user, int id);
    boolean addAvatar(int userId, MultipartFile file);
}
