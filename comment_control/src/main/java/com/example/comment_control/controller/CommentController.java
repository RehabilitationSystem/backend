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
        commentService.addComment(comment);
    }
    @GetMapping("/parent")
    @UnInterception

    public List<Comment> listParentComments() {
        List<Comment> comments = commentService.listParentComment();
        return comments;
    }
    @GetMapping("/child")
    public List<Comment> listChildComments(@RequestParam Long parentId) {
        List<Comment> comments = commentService.listChildComment(parentId);
        return comments;
    }
    @CrossOrigin(origins = "*")
    @RequestMapping("/test")
    public Comment test(@RequestParam Comment comment){
        return comment;
    }

}
