package com.example.comment_control.service;



import com.example.comment_control.entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);
    List<Comment> listAll();
    Comment findById(String id);
}
