package com.example.news_control.service;

import com.example.news_control.entity.News;
import org.apache.ibatis.annotations.Param;

public interface NewsService {
    News getNewsDetail( int new_id); // 根据新闻的ID获取新闻详情
    int publishNews(News news);
    int updateNews(News news);
}
