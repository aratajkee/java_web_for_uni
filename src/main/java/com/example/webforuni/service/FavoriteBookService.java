package com.example.webforuni.service;

import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteBookService {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    public boolean deleteFromFavorite(int bookId) {
        User user = loginService.getCurrentUser();
        user.deleteBook(bookId);
        return userService.update(user, user.getId());
    }
}
