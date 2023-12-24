package com.example.login_authetic.service;


import com.example.login_authetic.entity.User;
import com.example.login_authetic.entity.Url;
import com.example.login_authetic.entity.UserInfo;
import com.example.login_authetic.entity.UserRole;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;


public interface UserService {
    public User login(User user);
    public void register(User user);

    Set<String> getRoles(String username);

    Set<String> getPermissions(Long userId);

    User getByPhone(String phone);

     void insertUrl(Url url);

     void insertRole(UserRole role);

     void changePassword(String newPassword,String oldPassword,Long userId);

    void verifyPassword(String oldPassword, Long userId);

    void changeInfo(User user);

    void changePermission(Long userId,Integer roleId);

    void releaseRedis(String token,Long userId);

    void loginDataRedis(String sign,HttpSession httpSession, Long userId,Long counter);

    List<User> getAllUsers();
}
