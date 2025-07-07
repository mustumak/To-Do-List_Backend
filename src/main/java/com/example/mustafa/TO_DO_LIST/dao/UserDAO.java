package com.example.mustafa.TO_DO_LIST.dao;

import com.example.mustafa.TO_DO_LIST.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
