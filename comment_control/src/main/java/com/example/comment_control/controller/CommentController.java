package com.example.comment_control.controller;
mport com.example.comment_control.entity.Comment;
import com.example.comment_control.service.CommentService;
import com.example.commons.annotation.UnInterception;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    private List<Comment> comments;
    @RequestMapping("/submitComment")
    @UnInterception
    public void addComment(@RequestBody Comment comment){
//       Comment comment=new Comment();
//       comment.setAuthorId(authorId);
//       comment.setContent(content);
//       comment.setDataTime(dataTime);
       commentService.addComment(comment);
    }
    @RequestMapping("/listComment")
    @UnInterception
    public List<Comment> getComments(){
        List<Comment>comments=commentService.listAll();
        return comments;
    }

}