package com.example.webforuni.controllers.page_controllers.books;

import com.example.webforuni.service.BookService;
import com.example.webforuni.user.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class BookLoadController {
    @Autowired
    BookService bookService;

    private static Book bookHolder = null;
    @GetMapping("/uploadBook")
    public String openUploadForm() {
        return "upload_book_form";
    }

    @ModelAttribute(name="book")
    public Book getBook() {
        return new Book();
    }

    @PostMapping("/uploadBook/create")
    public String createBook(@ModelAttribute(name="book") Book book) {
        bookHolder = new Book();
        bookHolder.setName(book.getName());
        bookHolder.setAuthor(book.getAuthor());
        return "upload_book_form";
    }

    @PostMapping(value = "/uploadBook/addFile")
    public String uploadBookForm(Model model, @RequestParam(name = "file") MultipartFile file) {
        String message = "";
        try {
            bookHolder.setFile(file.getBytes());
            bookHolder.setFileName(file.getOriginalFilename());
            message = "Uploaded file " + file.getOriginalFilename() + " !";
        } catch (IOException e) {
            message = "Error loading file! " + e.getMessage();
        }
        model.addAttribute("message", message);
        return "upload_book_form";
    }

    @PostMapping("/uploadBook/addCover")
    public String uploadCoverForm(Model model, @RequestParam(name="cover") MultipartFile cover) {
        String message = "";
        try {
            bookHolder.setCover(cover.getBytes());
            message = "Uploaded cover " + cover.getOriginalFilename() + " !";
        } catch (IOException e) {
            message = "Error loading cover " + e.getMessage() + " !";
        }
        model.addAttribute("message", message);
        return "upload_book_form";
    }

    @PostMapping("/uploadBook/upload")
    public String uploadBook(Model model) {
        String message = "";
        try {
            bookService.create(bookHolder);
            message = "Book uploaded!";
            bookHolder = null;
        } catch (Exception e) {
            message = "Error uploading book " + e.getMessage() + " !";
        }
        model.addAttribute("message", message);
        return "upload_book_form";
    }

}

