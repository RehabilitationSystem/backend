package com.example.comment_control.entity;

import java.time.LocalDate;
import java.util.Date;

public class Comment {
    private String authorId;
    private String authorName;
    private String content;
    private String dateTime;
    private Long commentId;
    private int is_Parent;

    @Override
    public String toString() {
        return "Comment{" +
                "authorId='" + authorId + '\'' +
                ", authorName='" + authorName + '\'' +
                ", content='" + content + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", commentId=" + commentId +
                ", is_Parent=" + is_Parent +
                '}';
    }

    public void setIs_Parent(int is_Parent) {
        this.is_Parent = is_Parent;
    }

    public int getIs_Parent() {
        return is_Parent;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    //    public Comment(String authorId,String content,String dataTime){
//        this.authorId=authorId;
//        this.dataTime=dataTime;
//        this.content=content;
//    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }
}
