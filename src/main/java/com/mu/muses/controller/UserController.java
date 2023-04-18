package com.mu.muses.controller;

import com.mu.muses.dto.Login;
import com.mu.muses.entity.Role;
import com.mu.muses.entity.User;
import com.mu.muses.service.RoleService;
import com.mu.muses.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Operation(summary = "用户登录")
    @PostMapping(value = "/api/account/login")
    @ResponseBody
    public ResponseEntity<String> login( @RequestBody Login login) {
        if (userService.findByID(login.id)==null){
            String str = "no account";
            return new ResponseEntity<String>(str, HttpStatus.NOT_FOUND);
        }
        else if (!userService.findByIdAndPassword(login.id, login.password)){
            String str = "wrong password";
            return new ResponseEntity<String>(str, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    @Operation(summary = "获取角色列表")
    @GetMapping(value = "/api/account/roles")
    @ResponseBody
    public List<Role> getRoleList(){
        return roleService.findAll();
    }

    @Operation(summary = "保存或新建角色")
    @PostMapping(value = "/api/account/saveRole")
    @ResponseBody
    public boolean saveRole(@RequestBody Role role){
        Role existRole = roleService.findRole(role.name);
        if (existRole!= null) {
            role.id = existRole.id;
        }
        if (roleService.save(role)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    @Operation(summary = "查询用户列表")
    @GetMapping(value = "/api/account/users")
    @ResponseBody
    public List<User> users(){
        return userService.findAll();
    }

    @Operation(summary = "保存或更新用户")
    @GetMapping(value = "/api/account/saveUser")
    @ResponseBody
    public boolean saveUser(@RequestBody User user){
        return userService.newUser(user);
    }


}
