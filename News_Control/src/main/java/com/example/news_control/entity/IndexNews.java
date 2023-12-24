package com.example.news_control.entity;

/**
 * @Description:TODO
 * @DateTime:24/12/2023 下午8:41
 * @param:
 **/
public class IndexNews {
    private int new_id;
    private String title;
    private String picUrl;

    @Override
    public String toString() {
        return "IndexNews{" +
                "new_id=" + new_id +
                ", title='" + title + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

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

    public String getImgUrl() {
        return picUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.picUrl = imgUrl;
    }
}
