package com.example.webforuni.service;

import com.example.webforuni.user.model.Book;

import java.util.List;

public interface BookService {
    void create(Book book);
    List<Book> readAll();
    Book read(int id);
    boolean delete(int id);
    boolean update(Book book, int id);


}
