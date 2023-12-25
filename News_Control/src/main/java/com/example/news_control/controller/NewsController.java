package com.example.news_control.controller;

import com.example.commons.annotation.UnInterception;
import com.example.commons.config.Constants;
import com.example.commons.config.JsonResult;
import com.example.commons.service.ImageUtil;
import com.example.news_control.entity.IndexNews;
import com.example.news_control.entity.News;
import com.example.news_control.service.NewsService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.PropertyPermission;

@RestController
@RequestMapping("/new")
public class NewsController {
    @Resource
    private ImageUtil imageUtil;
    @Resource
    NewsService newsService;
    @Resource
    RedisTemplate<String,News> redisTemplate;
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
    @PostMapping("/getNewsDetail")
    @UnInterception
    public JsonResult getNewsDetail(@RequestBody News rec_news){
        News news = newsService.getNewsDetail(rec_news.getNew_id());
        if (news == null){
            return new JsonResult("400","请求失败");
        }else {
            return new JsonResult(news);
        }
    }
    @UnInterception
    @PostMapping("/publishNews")
    public JsonResult<News> publishNews(@RequestBody News news){
        news.setCreate_time(new Date(System.currentTimeMillis()));
        int i = newsService.publishNews(news);
        news.setNew_id(i);
        return new JsonResult<>(news);
    }
    @UnInterception
    @PostMapping("/updateNews")
    public JsonResult<News> updateNews(@RequestBody News news){
        int i = newsService.updateNews(news);
        return new JsonResult<News>(news);
    }
    @UnInterception
    @PostMapping("/recentNews")
    public JsonResult getRecentNews(){
        // 启动redis服务，存储登录数据
        return new JsonResult(newsService.getRecentNews());
    }

    @UnInterception
    @GetMapping("/testRedis")
    public JsonResult testRedis() throws NoSuchFieldException {
        List<News> newsList = newsService.getRecentNews();
        newsService.putRedis(newsList);
        return new JsonResult(Constants.SUCCESS_CODE,"添加新闻成功！");
    }


    @UnInterception
    @PostMapping("/ManageNewsList")
    public JsonResult ManageNewsList(){
        return new JsonResult(newsService.ManageNewsList());
    }
    @SneakyThrows
    @UnInterception
    @PostMapping("/upload")
    public JsonResult upload(HttpServletRequest httpServletRequest,
                             @RequestParam("file") MultipartFile file, int new_id , HttpSession session) {
        String newFileName = imageUtil.upload(file,Constants.NEWS_UPLOAD_DIR);
        newsService.upload(new_id,newFileName);
        return new JsonResult(imageUtil.getImageIp(httpServletRequest,newFileName,Constants.NEWS_UPLOAD_DIR), Constants.SUCCESS_CODE,"上传成功");
    }
    @SneakyThrows
    @UnInterception
    @PostMapping("/getImages")
    public JsonResult getImages(HttpServletRequest httpServletRequest,
                             @RequestParam("new_id") int new_id , HttpSession session) {
        List<String> picList =  newsService.getNewsPic(new_id);
        List<String> urlList = new ArrayList<>();
        for (String s:picList) {
            urlList.add(imageUtil.getImageIp(httpServletRequest,s,Constants.NEWS_UPLOAD_DIR)) ;
        }
        return new JsonResult(urlList,Constants.SUCCESS_CODE,"获取成功");
    }

    @UnInterception
    @PostMapping("/test2")
    public JsonResult getRedis(@RequestParam("key") int key){
        List<News> list =new ArrayList<>();
        for(int i=1;i<key;i++) {
            list.add(newsService.getRedis("news:" + i));
        }
        return new JsonResult(list,Constants.SUCCESS_CODE,"成功");
    }

    @UnInterception
    @PostMapping("/test")
    public JsonResult getIndexNews(HttpServletRequest httpServletRequest){
        List<IndexNews> list = newsService.getIndexNews();
        for(IndexNews i : list){
            i.setImgUrl(imageUtil.getImageIp(httpServletRequest,i.getImgUrl(),Constants.NEWS_UPLOAD_DIR));
        }
        return new JsonResult(list,Constants.SUCCESS_CODE,"成功");
    }
}
