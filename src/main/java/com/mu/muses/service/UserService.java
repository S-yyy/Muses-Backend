package com.mu.muses.service;

import com.mu.muses.dao.RoleDao;
import com.mu.muses.dao.UserDao;
import com.mu.muses.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    String salt = "MusesSaltMuseSaltHelloHello";

    public User findByID(int id){
        return userDao.findById(id);
    }

    public boolean login(int id, String password){
        User user = userDao.findById(id);
        if (user == null || !user.password.equals(encode(password))){
            return false;
        }
        else{
            return true;
        }
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public String encode(String password){
        return DigestUtils.md5DigestAsHex((salt + salt + password + salt + salt).getBytes());
    }


    public boolean newUser(User user){
        user.password = encode(user.password);

        if(userDao.save(user)!=null){
            return true;}
        else {
            return false;
        }
    }

}
