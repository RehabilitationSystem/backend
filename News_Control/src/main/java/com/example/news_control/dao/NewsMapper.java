package com.example.news_control.dao;

import com.example.news_control.entity.News;
import com.example.news_control.entity.NewsStatus;
import com.example.news_control.entity.NewsType;
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
    @Insert("INSERT INTO news(title,content,create_time,publish_id,editor_id) " +
            "VALUES(#{title},#{content},#{create_time},#{publish_id},#{editor_id})")
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
            @Result(property = "editor_id", column = "editor_id")
    })
    List<News> getRecentNews();
    @Select("SELECT * FROM news")
    @Results({
            @Result(property = "new_id", column = "new_id")
    })
    List<Integer> getNewsId();

    @Select("SELECT * FROM news_status")
    @Results({
            @Result(property = "new_id", column = "new_id"),
            @Result(property = "status", column = "status"),
    })
    List<NewsStatus> getNewsStatus();

    @Select("SELECT * FROM news_type")
    @Results({
            @Result(property = "new_id", column = "new_id"),
            @Result(property = "type", column = "type"),
    })
    List<NewsType> getNewsType();
}
