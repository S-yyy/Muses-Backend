package com.mu.muses.dao;

import com.mu.muses.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Integer> {
    List<Role> findAll();

    Role findByName(String name);

}
