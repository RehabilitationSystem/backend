package com.example.news_control.service;

import com.example.commons.service.RedisService;
import com.example.news_control.dao.NewsMapper;
import com.example.news_control.entity.IndexNews;
import com.example.news_control.entity.News;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewServiceImpl implements NewsService{
    @Resource
    private NewsMapper newMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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

    @Override
    public List<News> getRecentNews() {
        return newMapper.getRecentNews();
    }

    @Override
    public void putRedis(List<News> news) throws NoSuchFieldException {
//        Long key = System.currentTimeMillis();
        for (News n:news) {
//            redisService.saveSingleNews(n.getNew_id(),n);
            redisService.saveSingleNews(n.getNew_id(),n);
        }
    }

    @Override
    public List<Integer> getNewsId() {
        return newMapper.getNewsId();
    }

    @CacheEvict(value = "news", key = "#new_id")
    @Override
    public void addNews(News news) {

        redisTemplate.opsForValue().set("news:" + news.getNew_id(), news);

    }

    @Cacheable(value = "news", key = "#new_id")
    @Override
    public News getNewsById(int newsId) {
        return (News) redisTemplate.opsForValue().get("news:" + newsId);
    }

    @CacheEvict(value = "news", key = "#new_id")

    public void deleteNews(int newsId) {

        redisTemplate.delete("news:" + newsId);

    }

    @Override
    public void upload(int new_id, String picUrl) {
        newMapper.uploadImage(new_id,picUrl);
    }

    @Override
    public List<String> getNewsPic(int new_id) {
        return newMapper.getNewsImages(new_id);
    }

    @Override
    public List<News> ManageNewsList() {
        return newMapper.ManageNewsList();
    }

    @Override
    public News getRedis(String key) {
        return redisService.getAllNews(key);
    }

    @Override
    public List<IndexNews> getIndexNews() {
        return newMapper.getIndexNews();
    }
}
