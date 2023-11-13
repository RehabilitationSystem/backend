package com.example.news_control.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
class NewController {
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

}
