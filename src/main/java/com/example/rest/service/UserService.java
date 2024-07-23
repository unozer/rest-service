package com.example.rest.service;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(String username, String password) {
        User newUser = new User(UUID.randomUUID().toString(), username, password);
        return userRepository.save(newUser);
    }

    public User updateUser(String id, String username, String password) {
        User user = userRepository.findById(id);
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(String id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
