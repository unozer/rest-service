package com.example.rest.service;

import com.example.rest.model.User;
import com.example.rest.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(String username, String password) {
        User newUser = new User(UUID.randomUUID().toString(), username, password);
        return userRepository.save(newUser);
    }

    public User updateUser(String id, String username, String password) {
        User user = new User(id, username, password);
        return userRepository.save(user);
    }

    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
