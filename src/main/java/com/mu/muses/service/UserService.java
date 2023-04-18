package com.mu.muses.service;

import com.mu.muses.dao.RoleDao;
import com.mu.muses.dao.UserDao;
import com.mu.muses.entity.Role;
import com.mu.muses.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    public User findByID(int id){
        return userDao.findById(id);
    }

    public boolean findByIdAndPassword(int id, String password){
        User user = userDao.findById(id);
        if (user == null || !user.password.equals(password)){
            return false;
        }
        else{
            return true;
        }
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public boolean newUser(User user){
        if(userDao.save(user)!=null){
            return true;}
        else {
            return false;
        }
    }
}
