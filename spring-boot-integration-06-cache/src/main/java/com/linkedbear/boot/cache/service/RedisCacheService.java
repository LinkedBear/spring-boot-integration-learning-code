package com.linkedbear.boot.cache.service;

import com.linkedbear.boot.cache.entity.User;
import com.linkedbear.boot.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisCacheService {

    @Autowired
    private UserMapper userMapper;
    
    @Cacheable(value = "getName")
    public String getName(Integer i) {
        System.out.println("getName invoke ......");
        return "name" + i;
    }
    
    @Cacheable(value = "getUser", cacheManager = "userCacheManager")
    public User getUser(Integer id) {
        return userMapper.get(id);
    }
}
