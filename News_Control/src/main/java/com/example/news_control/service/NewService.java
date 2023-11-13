package com.example.news_control.service;

import com.example.news_control.entity.News;
import org.apache.ibatis.annotations.Param;

interface NewService {
    News getNews(@Param("News_Id") Integer id); // 根据新闻的ID获取新闻详情
}
