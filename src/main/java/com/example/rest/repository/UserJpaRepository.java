package com.example.rest.repository;

import com.example.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, String> {

    // Metodi di query personalizzati utilizzando convenzione di denominazione

    User findByUsername(String username);

    User findByPassword(String password);

    List<User> findByUsernameStartingWith(String prefix);

    List<User> findByUsernameContaining(String keyword);

    List<User> findByOrderByUsername();

    long countByUsername(String username);

    // Medoti di query personalizzati utilizzando l'annotazione @Query con JPQL
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findUserByUsername(String username);

    // Metodo di query personallizati utilizzando l'annotazione @Query con query native
    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findUserByUsernameNative(String username);

}
