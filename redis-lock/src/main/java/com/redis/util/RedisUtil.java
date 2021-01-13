package com.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public long ttl(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public boolean setnx(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value, 50, TimeUnit.SECONDS);
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public void decrement(String key){
        stringRedisTemplate.opsForValue().decrement(key);
    }

}
