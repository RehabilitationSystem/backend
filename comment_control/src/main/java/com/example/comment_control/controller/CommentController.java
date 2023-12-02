package com.example.comment_control.controller;


import com.example.comment_control.entity.Comment;
import com.example.comment_control.service.CommentService;
import com.example.comment_control.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
//@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    private List<Comment> comments;
    @RequestMapping("/submitComment")
    public void addComment(@RequestParam String authorId,@RequestParam String content,@RequestParam String dataTime){
       Comment comment=new Comment();
       comment.setAuthorId(authorId);
       comment.setContent(content);
       comment.setDataTime(dataTime);
       commentService.addComment(comment);
    }

}
