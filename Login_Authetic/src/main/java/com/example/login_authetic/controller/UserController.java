package com.example.login_authetic.controller;

import com.example.commons.config.Constants;
import com.example.commons.service.JwtUtil;
import com.example.commons.config.JsonResult;
import com.example.login_authetic.entity.*;
import com.example.commons.annotation.UnInterception;
import com.example.commons.service.RedisService;
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
        User login = userService.login(user);
        Long userId = login.getUserId();
        String sign = JwtUtil.sign(phone, null);
        redisService.storeToken(sign,httpSession.getId());
        Set<String> permissions = userService.getPermissions(userId);
        //数据存到会话里
        httpSession.setAttribute("permissions",permissions);
        httpSession.setAttribute("login",login);
        //redis存储用户登录的状态
        redisService.storeStatus(userId);
        return new JsonResult<>(sign,Constants.SUCCESS_CODE,"登录成功！");
    }

    @PostMapping("/1.0/register")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult Register (@RequestBody @Validated({RegisterGroup.class,Default.class}) User user){
        userService.register(user);
        return new JsonResult(Constants.SUCCESS_CODE,"注册成功！");
    }

    @PostMapping("/1.0/url")
    public JsonResult<String> addUrl(@RequestBody @Validated Url url){
        userService.insertUrl(url);
        return new JsonResult(Constants.SUCCESS_CODE,"添加路由成功！");
    }

    @PutMapping("/1.0/")
    public JsonResult<String> changePassword(HttpSession session,@RequestBody @Validated PasswordChangeRequest pcRequest){
        User login = (User) session.getAttribute("login");
        userService.changePassword(pcRequest.getNewPassword(), pcRequest.getOldPassword(), login.getUserId());
        return new JsonResult(Constants.SUCCESS_CODE,"修改密码成功！");
    }

    @PostMapping("/1.0/role")
    public JsonResult<String> addRole(@RequestBody @Validated UserRole userRole){
        userService.insertRole(userRole);
        return new JsonResult(Constants.SUCCESS_CODE,"添加角色成功！");
    }

    @PatchMapping("/1.0/")
    public JsonResult<String> changeInfo(HttpSession session,@RequestBody @Validated UserInfo userInfo){
        User login = (User) session.getAttribute("login");
        userService.changeInfo(userInfo,login.getUserId());
        return new JsonResult(Constants.SUCCESS_CODE,"修改用户信息成功！");
    }

    @PutMapping("/1.0/permission/{roleId}")
    public JsonResult<String> changePermission(@PathVariable Integer roleId,HttpSession session){
        User login = (User) session.getAttribute("login");
        userService.changePermission(login.getUserId(), roleId);
        return new JsonResult(Constants.SUCCESS_CODE,"修改用户权限成功！");
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/1.0/leave")
    public JsonResult<String> leaveSystem(@RequestParam String token,HttpSession session){
        User login = (User) session.getAttribute("login");
        userService.releaseRedis(token,login.getUserId());
        return new JsonResult(Constants.SUCCESS_CODE,"注销用户成功！");
    }


}
