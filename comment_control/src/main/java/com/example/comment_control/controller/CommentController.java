package com.example.comment_control.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.comment_control.entity.Comment;
import com.example.comment_control.service.CommentService;
import com.example.commons.annotation.UnInterception;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*")
public class CommentController {
    @Resource
    private CommentService commentService;
    private List<Comment> comments;
    @UnInterception
    @RequestMapping("/addComment")
    public void addComment(@RequestBody Comment comment) {
//        JSON.parseArray("[" + JSONObject.toJSONString(comment) + "]");
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedTime = currentTime.format(formatter);

        System.out.println("当前具体时间：" + formattedTime);
        comment.setDateTime(formattedTime);

        //用登陆的用户id用户名和当前页面新闻id替换username,2101000928L和1L
        comment.setAuthorId(2101000928L);
        comment.setNewsId(1L);
        comment.setAuthorName("username");

        commentService.addComment(comment);
    }
    @GetMapping("/parent")
    @UnInterception

    public List<Comment> listParentComments() {
        List<Comment> comments = commentService.listParentComment();
//        System.out.println(comments.get(0));
        return comments;
    }
    @GetMapping("/child")
    public List<Comment> listChildComments(@RequestParam Long parentId) {
        List<Comment> comments = commentService.listChildComment(parentId);
        return comments;
    }
//    @CrossOrigin(origins = "*")
//    @RequestMapping("/test")
//    public Comment test(@RequestParam Comment comment){
//        return comment;
//    }

}
