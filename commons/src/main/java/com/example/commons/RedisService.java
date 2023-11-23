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



    /**
     * 保存会话id，并初始化序列计数器
     * @param token
     * @param sessionId 会话id
     */
    public void storeToken(String token, String sessionId) {
        redisTemplate.opsForValue().set("sessionid",sessionId);
        redisTemplate.opsForHash().put(token, "sessionid", sessionId);
        redisTemplate.opsForHash().put(token, "counter", 0);
        redisTemplate.expire(token, 15, TimeUnit.MINUTES);
    }

    /**
     * 序列计数器自增1
     * @param token
     */
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

    /**
     * 保存用户的登录状态，如果redis能查询的到数据为1，说明用户在登录状态。
     * @param phone 用户唯一标识符电话
     */
    public void storeStatus(String phone){
        redisTemplate.opsForHash().put(phone,"status",true);
        redisTemplate.expire(phone, 15, TimeUnit.MINUTES);
    }

    /**
     * 返回用户登录状态，若没有初始化，返回false
     * @param phone
     * @return
     */
    public Boolean getStatus(String phone){
        Boolean status;
        return (status=(Boolean) redisTemplate.opsForHash().get(phone,"status"))==null?false:status;
    }

    /**
     * 原子增加密码输入错误的次数
     * @param phone 用户唯一标识符，电话号码
     */
    public void inCreTryNumbers(String phone){
       redisTemplate.opsForHash().increment(phone,"numbers",1);
    }

    /**
     * 取得密码输入错误的次数,若没初始化，则初始化
     * @param phone 用户唯一标识符，电话号码
     */
    public Integer getTryNumbers(String phone){
        Integer number;
        number = (Integer) redisTemplate.opsForHash().get(phone,"numbers");
        if(number==null){
            redisTemplate.opsForHash().put(phone,"number",0);
            redisTemplate.expire(phone, 15, TimeUnit.MINUTES);
            number=0;
        }
        return number;
    }
}