package com.mu.muses.dao;

import com.mu.muses.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByIdAndPassword(int id, String password);
    List<User> findAll();
}
