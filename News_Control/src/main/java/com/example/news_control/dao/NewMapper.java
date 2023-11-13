package com.example.news_control.dao;

import com.example.news_control.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NewMapper {
    @Select("SELECT * FROM news WHERE News_Id = ?1")
    public News getNewsById(Integer id);

}
