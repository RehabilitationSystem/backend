package com.example.news_control.entity;

/**
 * @Description:TODO
 * @DateTime:10/12/2023 下午7:44
 * @param:
 **/
public class NewsStatus {
    int new_id;
    int status;

    @Override
    public String toString() {
        return "NewsStatus{" +
                "new_id=" + new_id +
                ", status=" + status +
                '}';
    }

    public int getNew_id() {
        return new_id;
    }

    public void setNew_id(int new_id) {
        this.new_id = new_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
