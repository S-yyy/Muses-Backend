package com.mu.muses.controller;

import com.mu.muses.config.RestResponse;
import com.mu.muses.config.ResultCode;
import com.mu.muses.dto.Login;
import com.mu.muses.entity.Role;
import com.mu.muses.entity.User;
import com.mu.muses.service.RoleService;
import com.mu.muses.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Api(tags = "用户")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Operation(summary = "用户登录")
    @CrossOrigin
    @PostMapping(value = "/api/account/login")
    @ResponseBody
    public RestResponse login(@RequestBody Login login) {
        if (userService.findByID(login.id)==null){
            return RestResponse.fail(ResultCode.FAIL);
        }
        else if (!userService.findByIdAndPassword(login.id, login.password)){
            return RestResponse.fail(ResultCode.FAIL);
        }
        else{
            return RestResponse.success();
        }
    }

    @Operation(summary = "获取角色列表")
    @CrossOrigin
    @GetMapping(value = "/api/account/roles")
    @ResponseBody
    public RestResponse getRoleList(){
        return RestResponse.success(roleService.findAll());
    }

    @Operation(summary = "保存或新建角色")
    @CrossOrigin
    @PostMapping(value = "/api/account/saveRole")
    @ResponseBody
    public RestResponse saveRole(@RequestBody Role role){
        Role existRole = roleService.findRole(role.name);
        if (existRole!= null) {
            role.id = existRole.id;
        }
        if (roleService.save(role)!=null){
            return RestResponse.success();
        }
        else {
            return RestResponse.fail(ResultCode.FAIL_UPDATE);
        }
    }

    @Operation(summary = "查询用户列表")
    @CrossOrigin
    @GetMapping(value = "/api/account/users")
    @ResponseBody
    public RestResponse users(){
        return RestResponse.success(userService.findAll());
    }

    @Operation(summary = "保存或更新用户")
    @CrossOrigin
    @GetMapping(value = "/api/account/saveUser")
    @ResponseBody
    public RestResponse saveUser(@RequestBody User user){
        if(!userService.newUser(user)){
            return RestResponse.fail(ResultCode.FAIL_UPDATE);
        }
        return RestResponse.success();
    }


}
