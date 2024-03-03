package com.example.login_authetic.service;

import com.example.commons.config.Constants;
import com.example.commons.service.RedisIdWorker;
import com.example.commons.service.RedisService;
import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.login_authetic.dao.UserMapper;
import com.example.login_authetic.entity.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private RedisService redisService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     *  根据电话号码查询用户，并且进行密码匹配。
     * @param input 输入的待验证用户对象
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User login(User input) {
        User user;
        String phone = input.getPhone();
        if((user=userMapper.getUserByPhone(phone))==null){
            throw new BusinessErrorException(BusinessMsgEnum.USER_NOT_EXISTED);
        }
        Long userId = user.getUserId();
//        用户是否已经登录
        redisService.callWithLock(userId+Constants.PREFIX_USER, new Callable() {
            @Override
            public Object call(){
                if(redisService.getStatus(userId)){
                    throw new BusinessErrorException(BusinessMsgEnum.USER_HAS_LOGIN);
                }
                return null;
            }
        });

        //初始化密码尝试次数
        checkTryNumbers(userId);

        if(!passwordEncoder.matches(input.getPassword(),user.getPassword())){
            //记录密码输错的次数
            redisService.inCreTryNumbers(userId);
            throw new BusinessErrorException(BusinessMsgEnum.PASSWORD_WRONG_EXISTED);
        }
        user.setPassword(null);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    void checkTryNumbers(Long userId){
        if(redisService.getTryNumbers(userId)>5){
            throw new BusinessErrorException(BusinessMsgEnum.TRY_MUCH_NUMBERS);
        }
    }




    @Transactional(rollbackFor = Exception.class)
    @Async("threadPoolTaskExecutor")
    public void loginDataRedis(String sign,HttpSession httpSession, Long userId,Long counter){
        redisService.storeToken(sign,httpSession.getId(),counter);
        //redis存储用户登录的状态
        redisService.storeStatus(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getUserList();
    }


    /**
     * 判断电话号码是否被注册，插入用户数据到数据库
      @param input 输入的待验证用户对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User input) {
        //同步锁，针对，某个电话号同一时间只能注册一个
        redisService.callWithLock(Constants.PHONE+input.getPhone(), new Callable() {
            @Override
            public Object call(){
                //        用户初始化个人信息
                input.setUserId(redisIdWorker.nextId(Constants.PREFIX_USER));
                input.setGender(3);
                input.setAvatar(Constants.DEFAULT_AVATAR);
                if(userMapper.getUserByPhone(input.getPhone())!=null){
                    throw new BusinessErrorException(BusinessMsgEnum.USER_IS_EXISTED);
                }
                input.setPassword(passwordEncoder.encode(input.getPassword()));
                if(userMapper.insertUser(input)==0){
                    throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
                }
                return null;
            }
        });
    }

    @Override
    public Set<String> getRoles(String username) {
        return null;
    }

    /**
     * 获取用户权限
     * @param userId 用户唯一标识符
     * @return 返回权限集合
     */
    @Override
    public Set<String> getPermissions(Long userId) {
        return userMapper.getPermissions(userId);
    }

    @Override
    public User getByPhone(String phone) {

        return null;
    }

    @Override
    public void insertUrl(Url url) {
        if(userMapper.insertUrl(url.getUrl(),url.getRoleId())==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
        }
    }

    @Override
    public void insertRole(UserRole role) {
        if(userMapper.insertRole(role.getRoleId(),role.getRole())==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
        }
    }

    /**
     * 修改密码，需要之前的密码作为验证
     * @param newPassword 新的密码
     * @param oldPassword 旧密码
     * @param userId 用户唯一标识符
     */
    @Override
    public void changePassword(String newPassword, String oldPassword,Long userId) {
        User user;
        if((user=userMapper.getUserByUseId(userId))==null){
            throw new BusinessErrorException(BusinessMsgEnum.USER_NOT_EXISTED);
        }
        if(!passwordEncoder.matches(oldPassword,user.getPassword())){
            throw new BusinessErrorException(BusinessMsgEnum.PASSWORD_WRONG_EXISTED);
        }
//        密码尽量不传输
        user.setPassword(null);
        if(userMapper.UpdatePassword(passwordEncoder.encode(newPassword),userId)==0){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
    }

    @Override
    public void verifyPassword(String oldPassword, Long userId) {
        User user;
        if((user=userMapper.getUserByUseId(userId))==null){
            throw new BusinessErrorException(BusinessMsgEnum.USER_NOT_EXISTED);
        }
        if(!passwordEncoder.matches(oldPassword,user.getPassword())){
            throw new BusinessErrorException(BusinessMsgEnum.PASSWORD_WRONG_EXISTED);
        }
        user.setPassword(null);
    }


    @Override
    public void changeInfo(User user) {
        if(userMapper.UpdateInfo(user)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
        }
    }

    @Override
    public void changePermission(Long userId, Integer roleId) {
        if(userMapper.UpdatePermission(roleId,userId)==0){
            throw new BusinessErrorException(BusinessMsgEnum.DATA_INSERT_EXCEPTION);
        }
    }



    /**
     * 释放redis有关登录的数据，实现退出登录.
     * 删除token主键和phone主键的数据
     */
    @Override
    public void releaseRedis(String token,Long userId) {
        redisService.clearLogin(token,userId);
    }
}
