package com.example.login_authetic.controller;

import com.example.commons.JwtUtil;
import com.example.commons.config.JsonResult;
import com.example.login_authetic.entity.*;
import com.example.commons.annotation.UnInterception;
import com.example.commons.RedisService;
import com.example.login_authetic.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.groups.Default;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);


    @GetMapping("/1.0/test")
    public String test(){
        return "hello";
    }


    @PostMapping("/1.0/login")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult<String> Login(@RequestBody @Validated({LoginGroup.class, Default.class}) User user, HttpSession httpSession){
        String phone = user.getPhone();
        userService.login(user);
        String sign = JwtUtil.sign(phone, null);
        redisService.storeToken(sign,httpSession.getId());
        Set<String> permissions = userService.getPermissions(phone);
        httpSession.setAttribute("permissions",permissions);
        redisService.storeStatus(phone);
        return new JsonResult<>(sign,"200","登录成功！");
    }

    @PostMapping("/1.0/register")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult Register (@RequestBody @Validated({RegisterGroup.class,Default.class}) User user){
        userService.register(user);
        return new JsonResult("200","注册成功！");
    }

    @PostMapping("/1.0/url")
    public JsonResult<String> addUrl(@RequestBody @Validated Url url){
        userService.insertUrl(url);
        return new JsonResult("200","添加路由成功！");
    }

    @PostMapping("/1.0/role")
    public JsonResult<String> addRole(@RequestBody @Validated UserRole userRole){
        userService.insertRole(userRole);
        return new JsonResult("200","添加角色成功！");
    }
}
