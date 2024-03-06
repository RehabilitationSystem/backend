package com.example.authetic_system.service;


import com.example.authetic_system.entity.hide.Patient;
import com.example.authetic_system.entity.hide.Url;
import com.example.authetic_system.entity.hide.User;
import com.example.authetic_system.entity.hide.UserRole;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Set;


public interface UserService {
    public User login(User user);
    public void register(User user);

    public void pRegister(Patient patient);

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
