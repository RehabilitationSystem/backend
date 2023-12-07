package com.example.news_control.service;

import com.example.commons.service.RedisService;
import com.example.news_control.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsService {
    News getNewsDetail(int new_id); // 根据新闻的ID获取新闻详情
    int publishNews(News news);
    int updateNews(News news);
    List<News> getRecentNews();
    void putRedis(List<News> news) throws NoSuchFieldException;

    List<Integer> getNewsId();
    void addNews(News news);
    News getNewsById(int newsId);
    void deleteNews(int newsId);
}
