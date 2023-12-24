package com.example.news_control.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class News {

    public int getNew_id() {
        return new_id;
    }

    public void setNew_id(int new_id) {
        this.new_id = new_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getPublish_id() {
        return publish_id;
    }

    public void setPublish_id(Long publish_id) {
        this.publish_id = publish_id;
    }

    public Long getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(Long editor_id) {
        this.editor_id = editor_id;
    }
    @Id
    private int new_id;
    private String title;
    private String content;
    private Date create_time;
    private Long publish_id;
    private Long editor_id;
    private String status;

    @Override
    public String toString() {
        return "News{" +
                "new_id=" + new_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", create_time=" + create_time +
                ", publish_id=" + publish_id +
                ", editor_id=" + editor_id +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
}
