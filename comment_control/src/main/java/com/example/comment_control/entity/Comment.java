package com.example.comment_control.entity;

import java.time.LocalDate;

public class Comment {
    private String authorId;
    private String content;
    private String dataTime;
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

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public String getDataTime() {
        return dataTime;
    }
}
