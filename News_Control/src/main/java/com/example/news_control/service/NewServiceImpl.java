package com.example.news_control.service;

import com.example.news_control.dao.NewsMapper;
import com.example.news_control.entity.News;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NewServiceImpl implements NewsService{
    @Autowired
    private NewsMapper newMapper;
    @Override
    public News getNewsDetail(int new_id) {
        return newMapper.getNewsDetail(new_id);
    }

    @Override
    public int publishNews(News news) {
        return newMapper.publishNews(news);
    }

    @Override
    public int updateNews(News news) {
        return newMapper.updateNews(news);
    }

}
