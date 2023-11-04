package com.example.newsystem01.service;

import com.example.newsystem01.dao.UserMapper;
import com.example.newsystem01.entity.User;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;


    @Override
    public User getUserByName(int name) {
        User user = userMapper.getUser(1);
        return user;
    }
}
