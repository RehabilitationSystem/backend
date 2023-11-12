package com.example.login_authetic.service;


import com.example.login_authetic.entity.User;


public interface UserService {
    public User login(User user);
    public void register(User user);
}
