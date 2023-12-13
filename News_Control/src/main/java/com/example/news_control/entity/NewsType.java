package com.example.news_control.entity;

/**
 * @Description:TODO
 * @DateTime:10/12/2023 下午7:44
 * @param:
 **/
public class NewsType {
    int new_id;
    int type;

    @Override
    public String toString() {
        return "NewsType{" +
                "new_id=" + new_id +
                ", type=" + type +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNew_id() {
        return new_id;
    }

    public void setNew_id(int new_id) {
        this.new_id = new_id;
    }
}
