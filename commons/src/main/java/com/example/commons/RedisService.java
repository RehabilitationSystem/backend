package com.example.commons;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final static Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void storeToken(String token, String sessionId) {
        redisTemplate.opsForValue().set("sessionid",sessionId);
        redisTemplate.opsForHash().put(token, "sessionid", sessionId);
        redisTemplate.opsForHash().put(token, "counter", 0);
        redisTemplate.expire(token, 15, TimeUnit.MINUTES);
    }

    public void incrementCounter(String token) {
        Integer count = (Integer) redisTemplate.opsForHash().get(token, "counter");
        redisTemplate.opsForHash().put(token, "counter", ++count);
    }

    public Integer getCounter(String token) {
        return (Integer) redisTemplate.opsForHash().get(token, "counter");
    }

    public String getSessionId(String token) {
        return (String) redisTemplate.opsForHash().get(token, "sessionid");
    }

}