package com.example.news_control.controller;

import com.example.commons.annotation.UnInterception;
import com.example.news_control.entity.News;
import com.example.news_control.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new")
public class NewsController {
    @Autowired
    NewsService newsService;
    @RequestMapping("/hello")
    @UnInterception
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/getById")
    @UnInterception
    public News getNews(int new_id){
//        newsService.getNews(new_id);
        return newsService.getNews(new_id);
    }

}
