package com.example.newsystem01.service;

import com.example.newsystem01.dao.UserMapper;
import com.example.newsystem01.entity.User;
import com.example.newsystem01.listener.MyEvent;
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

    /**
     * 发布事件
     * @return
     */
    public User getUser2() {
        User user = new User(1, "倪升武", "123456");
        // 发布事件
        MyEvent event = new MyEvent(this, user);
        applicationContext.publishEvent(event);
        return user;
    }


}
