package com.example.comment_control.service;

import com.example.comment_control.dao.CommentMapper;

import com.example.comment_control.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public void addComment(Comment comment) {
        commentMapper.addComment(comment);
    }

    @Override
    public List<Comment> listAll() {
        return commentMapper.listAll();
    }

    @Override
    public Comment findById(String id) {
        return commentMapper.findById(id);
    }

}
