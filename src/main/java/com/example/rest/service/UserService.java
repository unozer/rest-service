package com.example.rest.service;

import com.example.rest.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User createUser(String username, String password) {
        User newUser = new User(UUID.randomUUID().toString(), username, password);
        users.add(newUser);
        return newUser;
    }

    public User updateUser(String id, String username, String password) {
        User user = getUserById(id);
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
        }
        return user;
    }

    public boolean deleteUser(String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}
