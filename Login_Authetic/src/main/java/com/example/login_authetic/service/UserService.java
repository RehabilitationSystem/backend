package com.example.login_authetic.service;


import com.example.login_authetic.entity.User;


public interface UserService {
    User getUserByName(int name);

    void insertUser(User user);

    User getUser();

    User getUser2();
}
