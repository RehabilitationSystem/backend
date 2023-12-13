package com.example.news_control.service;

import com.example.commons.service.RedisService;
import com.example.news_control.dao.NewsMapper;
import com.example.news_control.entity.News;
import com.example.news_control.entity.NewsStatus;
import com.example.news_control.entity.NewsType;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;

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
    public List<NewsStatus> getNewsStatus() {
        return newMapper.getNewsStatus();
    }

    @Override
    public List<NewsType> getNewsType() {
        return newMapper.getNewsType();
    }
}
