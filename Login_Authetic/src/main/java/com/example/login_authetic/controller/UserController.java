package com.example.login_authetic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
class UserController {
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
