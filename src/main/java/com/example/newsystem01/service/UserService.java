package com.example.newsystem01.service;


import com.example.newsystem01.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User getUserByName(int name);
}
