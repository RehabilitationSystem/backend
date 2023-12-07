package com.example.commons.service;

import com.example.commons.config.Constants;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    private final static Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;



    //设置默认尝试等待时间
    private final static int DEFAULT_LOCK_REGISTRY_TIME = 500;

    //尝试次数
    private final static int DEFAULT_LOCK_REGISTRY_NUMBERS = 3;
    //自定义lock key前缀
    private final static String LOCK_PREFIX = "LOCK:CUSTOMER_BALANCE";

    /**
     * 保存会话id，并初始化序列计数器
     * @param token
     * @param sessionId 会话id
     */
    public void storeToken(String token, String sessionId,Long value) {
        redisTemplate.opsForHash().put(token, Constants.SESSION_KEY, sessionId);
        redisTemplate.opsForHash().put(token, Constants.COUNTER_KEY, value);
        redisTemplate.expire(token, 15, TimeUnit.MINUTES);
    }

    /**
     * 序列计数器自增1
     * @param token
     */
    public void incrementCounter(String token) {
        Long count = (Long) redisTemplate.opsForHash().get(token, Constants.COUNTER_KEY);
        redisTemplate.opsForHash().put(token, Constants.COUNTER_KEY, ++count);
    }

    public Long getCounter(String token) {
        return (Long) redisTemplate.opsForHash().get(token, Constants.COUNTER_KEY);
    }

    public String getSessionId(String token) {
        return (String) redisTemplate.opsForHash().get(token, Constants.SESSION_KEY);
    }

    /**
     * 保存用户的登录状态，如果redis能查询的到数据为1，说明用户在登录状态。
     * @param userId 用户唯一标识符
     */
    public void storeStatus(Long userId){
        String key = String.valueOf(userId);
        redisTemplate.opsForHash().put(key,Constants.STATUS_KEY,true);
        redisTemplate.expire(key, 15, TimeUnit.MINUTES);
    }

    /**
     * 返回用户登录状态，若没有初始化，返回false
     * @param userId 用户唯一标识符，不明文展示
     * @return
     */
    public Boolean getStatus(Long userId){
        Boolean status;
        return (status=(Boolean) redisTemplate.opsForHash().get(String.valueOf(userId),Constants.STATUS_KEY))==null?false:status;
    }

    /**
     * 原子增加密码输入错误的次数
     * @param userId 用户唯一标识符
     */
    @Async
    @SneakyThrows
    public void inCreTryNumbers(Long userId){
       redisTemplate.opsForHash().increment(String.valueOf(userId),Constants.NUMBER_KEY,1);
    }

    /**
     * 取得密码输入错误的次数,若没初始化，则初始化
     * @param userId 用户唯一标识符
     */
    public Integer getTryNumbers(Long userId){
        Integer number;
        String key = String.valueOf(userId);
        number = (Integer) redisTemplate.opsForHash().get(key,Constants.NUMBER_KEY);
        if(number==null){
            redisTemplate.opsForHash().put(key,Constants.NUMBER_KEY,0);
            redisTemplate.expire(key, 15, TimeUnit.MINUTES);
            number=0;
        }
        return number;
    }

    /**
     * 清理登录时缓存的数据。
     * @param token 登录令牌
     * @param userId 用户唯一主键
     */
    public void clearLogin(String token,Long userId){
        String key = String.valueOf(userId);
        redisTemplate.opsForHash().delete(token,Constants.COUNTER_KEY);
        redisTemplate.opsForHash().delete(token,Constants.SESSION_KEY);
        redisTemplate.opsForHash().delete(key,Constants.NUMBER_KEY);
        redisTemplate.opsForHash().delete(key,Constants.STATUS_KEY);
    }

    /**
     * 创建分布式锁对象
     * @param redisTemplate redis操作对象
     * @param lockKey 分布式锁键key
     * @param lockValue 分布式锁的值，共享资源
     * @return
     */
    public RedisDistributedLock getLock(RedisTemplate<String, Object> redisTemplate,String lockKey, String lockValue) {
        return new RedisDistributedLock(redisTemplate, lockKey, lockValue);
    }

    /**
     * 分布式锁，进行处理的方法
     * @param lockKey 锁的键key
     * @param callable 要执行的任务
     * @param <T>
     */
    @SneakyThrows
    public <T> void callWithLock(T lockKey,Callable callable) {
        //自定义lock key
        String string = lockKey.toString();
        //将UUID当做value，确保唯一性
        String lockReference = UUID.randomUUID().toString();
        RedisDistributedLock lock = getLock(redisTemplate, string, lockReference);
        try {
            if (!lock.lockWithRetry(DEFAULT_LOCK_REGISTRY_NUMBERS,DEFAULT_LOCK_REGISTRY_TIME)) {
                throw new Exception("lock加锁失败");
            }
            callable.call();
        } finally {
            lock.unlockWithRetry(DEFAULT_LOCK_REGISTRY_NUMBERS,DEFAULT_LOCK_REGISTRY_TIME);
        }
    }
    public <T> void saveSingleNews(int key,T value) throws NoSuchFieldException {
        /**
        * @Author: Liu
        * @Description: 保存新闻列表，存入redis缓存
        * @Params: [news]
        * @Return: void
        **/
        redisTemplate.opsForValue().set(String.valueOf(key),value);
        redisTemplate.opsForValue().set(String.valueOf(key),value);
    }
    public <T> T getAllNews(int key){
        return (T) redisTemplate.opsForValue().get(key);
    }
}