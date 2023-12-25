package com.example.news_control.entity;

/**
 * @Description:TODO
 * @DateTime:19/12/2023 下午9:34
 * @param:
 **/
public class NewsPic {
    private int new_id;
    private String picUrl;

    @Override
    public String toString() {
        return "NewsPic{" +
                "new_id=" + new_id +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public int getNew_id() {
        return new_id;
    }

    public void setNew_id(int new_id) {
        this.new_id = new_id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
