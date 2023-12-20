package com.example.news_control.controller;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.config.JsonResult;
import com.example.news_control.entity.News;
import com.example.news_control.entity.NewsStatus;
import com.example.news_control.service.NewsService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/new")
public class NewsController {
    @Resource
    NewsService newsService;
    @Resource
    RedisTemplate<String,News> redisTemplate;
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/getNewsDetail")
    public JsonResult getNewsDetail(@RequestBody News rec_news){
        News news = newsService.getNewsDetail(rec_news.getNew_id());
        if (news == null){
            return new JsonResult("400","请求失败");
        }else {
            return new JsonResult(news);
        }

    }

    @PostMapping("/publishNews")
    public JsonResult<News> publishNews(News news){
        news.setCreate_time(new Date(System.currentTimeMillis()));
        int i = newsService.publishNews(news);

        news.setNew_id(i);
        return new JsonResult<>(news);
    }
    @PostMapping("/updateNews")
    public JsonResult<News> updateNews(News news){
        int i = newsService.updateNews(news);
        return new JsonResult<News>(news);
    }
    @PostMapping("/recentNews")
    public JsonResult getRecentNews(){
        // 启动redis服务，存储登录数据
        return new JsonResult(newsService.getRecentNews());
    }

    @GetMapping("/testRedis")
    public JsonResult testRedis() throws NoSuchFieldException {
        List<News> newsList = newsService.getRecentNews();
        newsService.putRedis(newsList);
        return new JsonResult(Constants.SUCCESS_CODE,"添加新闻成功！");
    }

    @PostMapping("/status")
    public JsonResult getStatus(){

        return new JsonResult<>(newsService.getNewsStatus());
    }
    @PostMapping("/type")
    public JsonResult getType(){
        return new JsonResult<>(newsService.getNewsType());
    }
}
