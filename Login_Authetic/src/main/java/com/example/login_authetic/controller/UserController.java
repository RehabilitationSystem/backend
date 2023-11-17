package com.example.login_authetic.controller;

import com.alibaba.fastjson.JSON;
import com.example.commons.JwtUtil;
import com.example.commons.config.JsonResult;
import com.example.login_authetic.entity.LoginGroup;
import com.example.login_authetic.entity.RegisterGroup;
import com.example.login_authetic.entity.User;
import com.example.commons.UnInterception;
import com.example.login_authetic.service.RedisService;
import com.example.login_authetic.service.UserService;
import jakarta.annotation.Resource;
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

    @PostMapping("/1.0/redis")
    @UnInterception
    public void test(){
        //测试redis的string类型
        redisService.setString("weichat","程序员私房菜");
        logger.info("我的微信公众号为：{}", redisService.getString("weichat"));

        // 如果是个实体，我们可以使用json工具转成json字符串，
        User user = new User("15777936513","wuhu","123456");
        redisService.setString("userInfo", JSON.toJSONString(user));
        logger.info("用户信息：{}", redisService.getString("userInfo"));

    }


    @PostMapping("/1.0/login")
    @UnInterception
    public JsonResult<String> Login(@RequestBody @Validated({LoginGroup.class, Default.class}) User user){
        //使用shiro编写认证操作
        userService.login(user);
        String sign = JwtUtil.sign(user.getPhone(), null);
        return new JsonResult<>(sign,"200","登录成功！");
    }

    @PostMapping("/1.0/register")
    public JsonResult Register (@RequestBody @Validated({RegisterGroup.class,Default.class}) User user){
        userService.register(user);
        return new JsonResult("200","注册成功！");
    }


}
