package com.example.webforuni.controllers.page_controllers.books;

import com.example.webforuni.service.BookService;
import com.example.webforuni.service.FileConverterService;
import com.example.webforuni.service.LoginService;
import com.example.webforuni.service.UserService;
import com.example.webforuni.user.model.Book;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    BookService bookService;

    @Autowired
    LoginService loginService;

    @Autowired
    FileConverterService converterService;

    @Autowired
    UserService userService;

    @GetMapping("/library")
    public String openLibrary() {
        return "library_page";
    }

    @ModelAttribute(name="books")
    public List<Book> getBooks() {
        return bookService.readAll();
    }

    @ModelAttribute(name="currentUser")
    public User getCurrentUser() {
        return loginService.getCurrentUser();
    }

    @ModelAttribute(name="currentUserRole")
    public String getCurrentUserRole(){
        return getCurrentUser() != null
                ? getCurrentUser().getRole()
                : User.Role.CLIENT.toString();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadBook(@PathVariable int id) {
        Book book = bookService.read(id);
        System.out.println(book.getName() + "\n" + book.getAuthor());
        Resource bookRes = FileConverterService.convertToRes(book.getFile(), book.getName());
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + book.getFileName() + "\"").body(bookRes);
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/library";
    }

    @GetMapping("/book/addToFav/{id}")
    public String addToFavorite(@PathVariable int id) {
        User user = getCurrentUser();
        user.addBook(id);
        userService.update(user, user.getId());
        return "redirect:/library";
    }

}
