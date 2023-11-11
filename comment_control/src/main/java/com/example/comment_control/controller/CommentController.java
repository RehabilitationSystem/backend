package com.example.comment_control.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
class CommentController {
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
