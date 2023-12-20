package com.example.comment_control.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Data
public class Comment {
    private String authorId;
    private String authorName;
    private String content;
    private String dateTime;
    private Long commentId;
    private int is_Parent;
    private String target;
    private List<Comment> replyComment;

}
