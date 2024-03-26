package com.example.webforuni.controllers.page_controllers.books;

import com.example.webforuni.service.BookService;
import com.example.webforuni.service.FavoriteBookService;
import com.example.webforuni.service.LoginService;
import com.example.webforuni.user.model.Book;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MyBooksController {

    @Autowired
    BookService bookService;

    @Autowired
    LoginService loginService;

    @Autowired
    FavoriteBookService favoriteBookService;

    @GetMapping("/myBooks")
    public String openMyBooks() {
        return "my_books";
    }

    @ModelAttribute(name="currentUser")
    public User getCurrentUser() {
        return loginService.getCurrentUser();
    }

    @ModelAttribute(name="favBooks")
    public List<Book> getFavBooks() {
        List<Book> favBooks = new ArrayList<>();
        if (getCurrentUser() != null) {
            for (Integer id : getCurrentUser().getFavoriteBooksId()) {
                favBooks.add(bookService.read(id));
            }
        }
        return favBooks;
    }

    @GetMapping("/myBooks/delete/{id}")
    public String deleteFromFavorite(@PathVariable int id) {
        favoriteBookService.deleteFromFavorite(id);
        return "redirect:/myBooks";
    }
}
