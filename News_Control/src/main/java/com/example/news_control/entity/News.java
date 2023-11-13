package com.example.news_control.entity;

public class News {
    private Integer news_id;
    private String news_title;
    private String news_info;
    private String create_time;
    private String update_time;

    @Override
    public String toString() {
        return "News{" +
                "news_id=" + news_id +
                ", news_title='" + news_title + '\'' +
                ", news_info='" + news_info + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_info() {
        return news_info;
    }

    public void setNews_info(String news_info) {
        this.news_info = news_info;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
