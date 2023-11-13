package com.example.login_authetic.controller;

import com.example.commons.config.JsonResult;
import com.example.login_authetic.entity.LoginGroup;
import com.example.login_authetic.entity.RegisterGroup;
import com.example.login_authetic.entity.User;
import com.example.login_authetic.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.groups.Default;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/1.0/login")
    public JsonResult<User> Login(@RequestBody @Validated({LoginGroup.class, Default.class}) User user){
        //使用shiro编写认证操作
        userService.login(user);
        return new JsonResult<>("200","登录成功！");
    }

    @PostMapping("/1.0/register")
    public JsonResult Register (@RequestBody @Validated({RegisterGroup.class,Default.class}) User user){
        userService.register(user);
        return new JsonResult("200","注册成功！");
    }
}
