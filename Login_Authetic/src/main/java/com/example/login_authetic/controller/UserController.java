package com.example.login_authetic.controller;

import com.example.commons.JwtUtil;
import com.example.commons.config.JsonResult;
import com.example.login_authetic.entity.LoginGroup;
import com.example.login_authetic.entity.RegisterGroup;
import com.example.login_authetic.entity.User;
import com.example.commons.UnInterception;
import com.example.commons.RedisService;
import com.example.login_authetic.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.groups.Default;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @UnInterception
    public JsonResult<String> Login(@RequestBody @Validated({LoginGroup.class, Default.class}) User user, HttpSession httpSession){
        userService.login(user);
        String sign = JwtUtil.sign(user.getPhone(), null);
        redisService.storeToken(sign,httpSession.getId());
        return new JsonResult<>(sign,"200","登录成功！");
    }

    @PostMapping("/1.0/register")
    @UnInterception
    public JsonResult Register (@RequestBody @Validated({RegisterGroup.class,Default.class}) User user){
        userService.register(user);
        return new JsonResult("200","注册成功！");
    }


}
