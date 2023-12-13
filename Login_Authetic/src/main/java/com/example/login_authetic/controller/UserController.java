package com.example.login_authetic.controller;

import com.example.commons.annotation.MainTransaction;
import com.example.commons.annotation.WebLog;
import com.example.commons.config.Constants;
import com.example.commons.service.JwtUtil;
import com.example.commons.config.JsonResult;
import com.example.commons.service.MessageServiceImpl;
import com.example.commons.service.RedisIdWorker;
import com.example.login_authetic.entity.*;
import com.example.commons.annotation.UnInterception;
import com.example.commons.service.RedisService;
import com.example.login_authetic.entity.groupRule.InfoGroup;
import com.example.login_authetic.entity.groupRule.LoginGroup;
import com.example.login_authetic.entity.groupRule.RegisterGroup;
import com.example.login_authetic.service.UserService;
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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.Future;

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

    @GetMapping("/1.0/test/send")
    @UnInterception
    public void test(){
        messageService.sendMessage("Nihao");
    }

    @GetMapping("/1.0/test/receive")
    @UnInterception
    public void test1(){
        String s = messageService.doMessage();
        logger.error(s);
    }



    @SneakyThrows
    @PostMapping("/1.0/login")
    @WebLog(channel = "web",name = "登录",action = "login",saveFlag = true)
    @Transactional(rollbackFor = Exception.class)
    @UnInterception
    public JsonResult<String> Login(@RequestBody @Validated({LoginGroup.class, Default.class}) User user, HttpServletRequest httpServletRequest,HttpSession httpSession){
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
        String host = getHost((new URI(httpServletRequest.getRequestURL() + ""))) + "/images/" + login.getAvatar();
        login.setAvatar(host);
        return new JsonResult(new ArrayList<>(Arrays.asList(sign,counter,login)),Constants.SUCCESS_CODE,"登录成功！");
    }

    @PostMapping("/1.0/register")
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

    @PostMapping("/1.0/role")
    public JsonResult<String> addRole(@RequestBody @Validated UserRole userRole){
        userService.insertRole(userRole);
        return new JsonResult(Constants.SUCCESS_CODE,"添加角色成功！");
    }

    @PatchMapping("/1.0/")
    public JsonResult<String> changeInfo(HttpSession session,@RequestBody @Validated({InfoGroup.class,Default.class}) User user){
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
        return new JsonResult(allUsers,Constants.SUCCESS_CODE,"注销用户成功！");
    }


    @SneakyThrows
    @UnInterception
    @PostMapping("/1.0/upload")
    public JsonResult upload(HttpServletRequest httpServletRequest,
                                  @RequestParam("file") MultipartFile file,HttpSession session) {
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        //得到文件全名字
        String newFileName = uuid.toString() + suffixName;
        //创建文件
        //获取文件夹地址
        File fileDirectory = new File(Constants.FILE_UPLOAD_DIR);
        //文件地址
        File destFile = new File(Constants.FILE_UPLOAD_DIR +  newFileName);
        //文件夹不存在，就先创建文件夹
        if (!fileDirectory.exists()) {
            //如果文件夹创建失败就抛异常（fileDirectory.mkdir()）
            if (!fileDirectory.mkdir()) {
                //业务异常
            }
        }
        //到这，文件夹就肯定存在了。。
        try {
            //文件写入  file-->destFile
            //这样就把http传入的文件file写入指定的路径下的destFile文件中，完成了文件上传
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        User user = new User();
//        user.setAvatar(newFileName);
//        userService.changeInfo(user);
        //完成文件上传后，还需要指定http资源映射
        return new JsonResult(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/"
                + newFileName, Constants.SUCCESS_CODE,"上传成功");
    }

    //获取当前的ip和端口号
    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }



}
