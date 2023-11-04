package com.example.newsystem01.service;


import com.example.newsystem01.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    User getUserByName(int name);

    void insertUser(User user);

    User getUser();

    User getUser2();
}
