package com.example.commons.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class RedisDistributedLock {

    private static final String LOCK_SCRIPT = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then redis.call('pexpire', KEYS[1], ARGV[2]) return true else return false end";
    private static final String UNLOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    private static final String LOCK_KEY_PREFIX = "lock:";
    private static final long LOCK_EXPIRE_TIME = 30000L;

    private RedisTemplate<String, Object> redisTemplate;
    private String lockKey;
    private String lockValue;
    private boolean locked = false;
    private String lockScript;
    private String unlockScript;

    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate, String lockKey, String lockValue) {
        this.redisTemplate = redisTemplate;
        this.lockKey = LOCK_KEY_PREFIX + lockKey;
        this.lockValue = lockValue;
        this.lockScript = new DefaultRedisScript<>(LOCK_SCRIPT, Boolean.class).getScriptAsString();
        this.unlockScript = new DefaultRedisScript<>(UNLOCK_SCRIPT, Long.class).getScriptAsString();
    }

    public boolean lock() {
        Object result = redisTemplate.execute(new DefaultRedisScript<>(lockScript, Boolean.class), Collections.singletonList(lockKey), lockValue, LOCK_EXPIRE_TIME);
        locked = (Boolean) result;
        return locked;
    }

    public void unlock() {
        if (locked) {
            redisTemplate.execute(new DefaultRedisScript<>(unlockScript, Long.class), Collections.singletonList(lockKey), lockValue);
        }
    }

    public boolean lockWithRetry(int retryCount, long retryInterval) throws InterruptedException {
        int count = 0;
        while (count < retryCount) {
            if (lock()) {
                return true;
            }
            count++;
            Thread.sleep(retryInterval);
        }
        return false;
    }

    public void unlockWithRetry(int retryCount, long retryInterval) throws InterruptedException {
        int count = 0;
        while (count < retryCount) {
            try {
                unlock();
                break;
            } catch (Exception e) {
                count++;
                Thread.sleep(retryInterval);
            }
        }
    }

}
