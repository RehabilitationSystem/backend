package com.example.news_control.dao;

import com.example.news_control.entity.News;
import org.apache.ibatis.annotations.*;

@Mapper
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
    News getNewsById(int new_id);

}
