package com.example.login_authetic.service;


import com.example.login_authetic.entity.User;

import java.util.Set;


public interface UserService {
    public User login(User user);
    public void register(User user);

    Set<String> getRoles(String username);

    Set<String> getPermissions(String username);

    User getByPhone(String phone);
}
