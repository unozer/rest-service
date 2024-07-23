package com.example.rest.repository;

import com.example.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public User findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{id}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User save(User user) {
        String sql;
        User existingUser = findById(user.getId());
        if (existingUser != null) {
            sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO users (username, password, id) VALUES (?, ?, ?)";
        }
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getId());
        return user;
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, rowMapper);
        return users.isEmpty() ? null : users.get(0);
    }
}
