package com.linkedbear.boot.cache.service;

import com.linkedbear.boot.cache.entity.User;
import com.linkedbear.boot.cache.mapper.UserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CachedUserService implements InitializingBean {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private CacheManager cacheManager;
    
    private Cache cache;
    
    @Override
    public void afterPropertiesSet() throws Exception {
//        this.cache = cacheManager.getCache("user");
    }
    
    public User get(Integer id) {
        // 1. 通过CacheManager拿到名为user的缓存对象Cache
        Cache cache = cacheManager.getCache("user");
        // 2. 从Cache中尝试获取一个指定id的User类型的对象
        User user = cache.get(id, User.class);
        // 3. 如果对象数据存在，则直接返回
        if (user != null) {
            return user;
        }
        // 4. 如果数据不存在，则需要查询数据库，并将查询的结果放入Cache中
        User userFromDatabase = userMapper.get(id);
        cache.put(id, userFromDatabase);
        return userFromDatabase;
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public List<User> findAllByName(String name) {
        return cache.get(name, () -> userMapper.findAllByNameLike(name));
    }
}
