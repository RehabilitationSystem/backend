package com.example.login_authetic.service;

import com.example.login_authetic.dao.UserMapper;
import com.example.login_authetic.entity.User;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public User getUserByName(int name) {
        User user = userMapper.getUser(1);
        return user;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) {
        // 插入用户信息
        userMapper.insertUser(user);
        // 手动抛出异常
//        throw new RuntimeException();
    }

    @Override
    public User getUser() {
        return new User(1,"zqq","dudud");
    }

    @Override
    public User getUser2() {
        return null;
    }

}
