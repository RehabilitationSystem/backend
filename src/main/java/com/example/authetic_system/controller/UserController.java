package com.example.authetic_system.controller;

import com.example.authetic_system.entity.hide.*;
import com.example.commons.annotation.WebLog;
import com.example.commons.config.Constants;
import com.example.commons.service.*;
import com.example.commons.config.JsonResult;
import com.example.commons.annotation.UnInterception;
import com.example.authetic_system.entity.groupRule.InfoGroup;
import com.example.authetic_system.entity.groupRule.LoginGroup;
import com.example.authetic_system.entity.groupRule.RegisterGroup;
import com.example.authetic_system.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.groups.Default;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/user")
class UserController {
    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private MessageServiceImpl messageService;

    @Resource
    private ImageUtil imageUtil;

    @GetMapping("/1.0/test/send")
    @UnInterception
    public void test(){
        messageService.sendMessage("asdasdadad");
    }


    @GetMapping("/1.0/test/receive")
    @UnInterception
    public void test1(){
        Object s = messageService.doMessage(String.class);
    }



    @SneakyThrows
    @PostMapping("/1.0/login")
    @WebLog(channel = "web",name = "登录",action = "login",saveFlag = true)
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult<String> Login(@RequestBody @Validated({LoginGroup.class, Default.class}) User user, HttpServletRequest httpServletRequest, HttpSession httpSession){
        User login = userService.login(user);
        Long userId = login.getUserId();
        httpSession.setAttribute("login",login);

        //启动redis服务,存储登录数据
        String sign = JwtUtil.sign(userId, null);
        Long counter = redisIdWorker.nextId(Constants.PREFIX_COUNTER);
        userService.loginDataRedis(sign, httpSession, userId,counter);

        //数据存到会话里
        Set<String> permissions = userService.getPermissions(userId);
        httpSession.setAttribute("permissions",permissions);
        String imageIp = imageUtil.getImageIp(httpServletRequest, login.getAvatar(), Constants.AVATAR_UPLOAD_DIR);
        login.setAvatar(imageIp);
        logger.info("计数器的值"+counter);
        return new JsonResult(new ArrayList<>(Arrays.asList(sign,counter,login)),Constants.SUCCESS_CODE,"登录成功！");
    }

    @PostMapping("/1.0/pRegister")
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult pRegister (@RequestBody Patient patient){
        userService.pRegister(patient);
        return new JsonResult(Constants.SUCCESS_CODE,"患者注册成功！");
    }

    @PostMapping("/1.0/Register")
    @Transactional(rollbackFor = Exception.class)
    @WebLog(channel = "web",name = "注册",action = "register",saveFlag = true)
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

    @PostMapping("/1.0/verify")
    public JsonResult<String> verifyPassword(HttpSession session,@RequestBody PasswordChangeRequest pcRequest){
        User login = (User) session.getAttribute("login");
        userService.verifyPassword(pcRequest.getOldPassword(), login.getUserId());
        return new JsonResult(Constants.SUCCESS_CODE,"旧密码正确！");
    }

    @PostMapping("/1.0/role")
    public JsonResult<String> addRole(@RequestBody @Validated UserRole userRole){
        userService.insertRole(userRole);
        return new JsonResult(Constants.SUCCESS_CODE,"添加角色成功！");
    }

    @PatchMapping("/1.0/")
    public JsonResult<String> changeInfo(HttpSession session,@RequestBody @Validated({InfoGroup.class}) User user){
        User login = (User) session.getAttribute("login");
        user.setUserId(login.getUserId());
        userService.changeInfo(user);
        return new JsonResult(Constants.SUCCESS_CODE,"修改用户信息成功！");
    }

    @PutMapping("/1.0/permission/{userId}/{roleId}")
    public JsonResult<String> changePermission(@PathVariable Long userId,@PathVariable Integer roleId,HttpSession session){
        userService.changePermission(userId, roleId);
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

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/1.0/all")
    public JsonResult<String> getUserList(){
        List<User> allUsers = userService.getAllUsers();
        return new JsonResult(allUsers,Constants.SUCCESS_CODE,"获取所有用户信息成功！");
    }


    /**
     * 上传文件
     * @param httpServletRequest
     * @param file
     * @param session
     * @return
     */
    @SneakyThrows
    @UnInterception
    @PostMapping("/1.0/upload")
    public JsonResult upload(HttpServletRequest httpServletRequest,
                                  @RequestParam("file") MultipartFile file,HttpSession session) {
        String newFileName = imageUtil.upload(file,Constants.AVATAR_UPLOAD_DIR);
        return new JsonResult(imageUtil.getImageIp(httpServletRequest,newFileName,Constants.AVATAR_UPLOAD_DIR), Constants.SUCCESS_CODE,"上传成功");
    }



}
