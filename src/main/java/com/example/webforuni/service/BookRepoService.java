package com.example.webforuni.service;

import com.example.webforuni.repository.BookRepository;
import com.example.webforuni.user.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRepoService implements BookService {

    @Autowired
    BookRepository repository;

    @Override
    public void create(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> readAll() {
        return repository.findAll();
    }

    @Override
    public Book read(int id) {
        return repository.findById(id).isPresent()
                ? repository.findById(id).get()
                : null;
    }

    @Override
    public boolean delete(int id) {
        if (repository.findById(id).isPresent()) {
            repository.delete(repository.findById(id).get());
        }
        return repository.findById(id).isPresent();
    }

    @Override
    public boolean update(Book book, int id) {
        if (repository.findById(id).isPresent()) {
            Book updatedBook = repository.findById(id).get();
            repository.delete(updatedBook);
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setName(book.getName());
            updatedBook.setFile(book.getFile());
            repository.save(updatedBook);
            return true;
        }
        return false;
    }
}
