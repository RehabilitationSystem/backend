package com.example.news_control.controller;

import com.example.news_control.entity.News;
import com.example.news_control.service.NewsService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class NewsController {
    @Autowired
    NewsService newsService;
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/getById")
    public News getNews(int new_id){
//        newsService.getNews(new_id);
        return newsService.getNews(new_id);
    }

}
