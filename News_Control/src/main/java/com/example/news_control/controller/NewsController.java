package com.example.news_control.controller;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.JsonResult;
import com.example.news_control.entity.News;
import com.example.news_control.service.NewsService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/new")
public class NewsController {
    @Resource
    NewsService newsService;
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/getNewsDetail")
    @UnInterception
    public JsonResult getNewsDetail(int new_id){
        News news = newsService.getNewsDetail(new_id);
        if (news == null){
            return new JsonResult("400","请求失败");
        }else {
            return new JsonResult(news);
        }

    }

    @PostMapping("/publishNews")
    public JsonResult publishNews(News news){
        news.setCreate_time(new Date(System.currentTimeMillis()));
        int i = newsService.publishNews(news);

        news.setNew_id(i);
        return new JsonResult(news);
    }
    @PostMapping("/updateNews")
    public JsonResult updateNews(News news){
        int i = newsService.updateNews(news);
        return new JsonResult(news);
    }

}
