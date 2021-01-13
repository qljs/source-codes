package com.redis.controller;

import com.redis.util.RedisUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    Redisson redisson;
    private RLock lock;

    @RequestMapping("/set")
    public String set(@RequestParam("key")String key, @RequestParam("value") String value){
//        String val = UUID.randomUUID().toString();
//        try{
//            boolean lock = redisUtil.setnx(key, val);
//            if(!lock){
//                System.out.println("set fail");
//                return "get lock fail";
//            }
//            Integer product = Integer.valueOf(redisUtil.get("product"));
//            if(product > 0){
//                redisUtil.decrement("product");
//            }
//        } finally {
//            if(val.equals(redisUtil.get(key))){
//                redisUtil.del(key);
//            }
//        }

        RLock lock = redisson.getLock(key);
        try {
            lock.lock();
            Integer product = Integer.valueOf(redisUtil.get("product"));
            if(product > 0){
                redisUtil.decrement("product");
            }

        } finally {
            lock.unlock();
        }

        return "success";
    }

    @RequestMapping("/get")
    public String get(@RequestParam("key")String key) {
        return redisUtil.get(key);

    }
}
