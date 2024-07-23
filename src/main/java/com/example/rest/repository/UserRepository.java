package com.example.rest.repository;

import com.example.rest.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(String id);
    User save(User user);
    void deleteById(String id);
    User findByUsername(String username);
}
