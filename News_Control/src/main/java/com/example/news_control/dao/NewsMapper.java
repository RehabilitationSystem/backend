package com.example.news_control.dao;

import com.example.news_control.entity.IndexNews;
import com.example.news_control.entity.News;
import com.example.news_control.entity.NewsPic;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NewsMapper {
    @Select("SELECT * FROM news WHERE new_id = #{new_id}")
    @Results({
            @Result(property = "new_id", column = "new_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "publish_id", column = "publish_id"),
            @Result(property = "editor_id", column = "editor_id")
    })
    News getNewsDetail(int new_id);
    @Insert("INSERT INTO news(title,content,create_time,publish_id,status,type) " +
            "VALUES(#{title},#{content},#{create_time},#{publish_id},#{status},#{type})")
    int publishNews(News news);
    @Update("UPDATE news " +
            "SET title = #{title},content = #{content} " +
            "WHERE new_id = #{new_id}")
    int updateNews(News news);
    @Select("SELECT * FROM news")
    @Results({
            @Result(property = "new_id", column = "new_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "create_time", column = "create_time"),
            @Result(property = "publish_id", column = "publish_id"),
            @Result(property = "editor_id", column = "editor_id"),
            @Result(property = "pic",column = "pic")
    })
    List<News> getRecentNews();
    @Select("SELECT * FROM news")
    @Results({
            @Result(property = "new_id", column = "new_id")
    })
    List<Integer> getNewsId();
    @Select("SELECT new_id,title,status,type,create_time,publish_id,editor_id FROM news")
    @Results({
            @Result(property = "new_id",column = "new_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "status",column = "status"),
            @Result(property = "type",column = "type"),
            @Result(property = "create_time",column = "create_time"),
            @Result(property = "publish_id",column = "publish_id"),
            @Result(property = "editor_id",column = "editor_id")
    })
    List<News> ManageNewsList();
    List<News> HotNews();

    @Insert("INSERT INTO newsPic(new_id,picUrl) VALUES (#{new_id},#{picUrl})")
    void uploadImage(int new_id, String picUrl);
    @Select("SELECT picUrl FROM newsPic WHERE new_id = #{new_id}")
    @Results({
            @Result(property = "picUrl", column = "picUrl")
    })
    List<String> getNewsImages(int news_id);
    @Select("""
            SELECT n.new_id, n.title, np.picUrl \s
            FROM news n \s
            JOIN ( \s
                SELECT new_id, MIN(picUrl) AS picUrl \s
                FROM newsPic \s
                GROUP BY new_id \s
            ) np ON n.new_id = np.new_id;""")
    @Results({
            @Result(property = "new_id",column = "new_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "picUrl",column = "picUrl")
    })
    List<IndexNews> getIndexNews();
}
