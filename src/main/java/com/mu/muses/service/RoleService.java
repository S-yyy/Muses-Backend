package com.mu.muses.service;

import com.mu.muses.dao.RoleDao;
import com.mu.muses.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public List<Role> findAll(){
        return roleDao.findAll();
    }

    public Role findRole(String name){
        return roleDao.findByName(name);
    }


    public Role save(Role role){
        return roleDao.save(role);
    }

}
