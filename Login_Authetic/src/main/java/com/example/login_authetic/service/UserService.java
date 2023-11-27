package com.example.login_authetic.service;


import com.example.login_authetic.entity.User;
import com.example.login_authetic.entity.Url;
import com.example.login_authetic.entity.UserInfo;
import com.example.login_authetic.entity.UserRole;

import java.util.Set;


public interface UserService {
    public User login(User user);
    public void register(User user);

    Set<String> getRoles(String username);

    Set<String> getPermissions(String phone);

    User getByPhone(String phone);

     void insertUrl(Url url);

     void insertRole(UserRole role);

     void changePassword(String newPassword,String oldPassword,String phone);

    void changeInfo(UserInfo userInfo, String phone);

    void changePermission(String phone,Integer roleId);

    void releaseRedis(String token,String phone);
}
