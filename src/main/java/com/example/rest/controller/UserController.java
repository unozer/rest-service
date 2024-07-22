package com.example.rest.controller;

import com.example.rest.exception.UserNotFoundException;
import com.example.rest.model.User;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user == null) {
           throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String username, @RequestParam String password) {
        User newUser = userService.createUser(username, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable String id,
            @RequestParam String username,
            @RequestParam String password
    ) {
        User updatedUser = userService.updateUser(id, username, password);
        if (updatedUser == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        } else {
            return updatedUser;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            throw new UserNotFoundException("User not found with id:" + id);
        }
    }
}
