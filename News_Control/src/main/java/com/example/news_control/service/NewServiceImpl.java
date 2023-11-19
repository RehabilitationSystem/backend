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
    public News getNews(int new_id) {
        return newMapper.getNewsById(new_id);
    }
}
