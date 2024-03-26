package com.example.webforuni.controllers.page_controllers.user;

import com.example.webforuni.service.UserService;
import com.example.webforuni.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User user) {
        service.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addFew")
    public ResponseEntity<?> createFew(@RequestBody User[] users) {
        for (User user : users) {
            service.create(user);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> readAll() {
        final List<User> userList = service.readAll();
        return userList!=null && !userList.isEmpty()
                ? new ResponseEntity<>(userList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readId(@PathVariable(name = "id") Integer id) {
        final User user = service.read(id);
        return user != null
            ? new ResponseEntity<>(user, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        return service.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody User user) {
        return service.update(user, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
