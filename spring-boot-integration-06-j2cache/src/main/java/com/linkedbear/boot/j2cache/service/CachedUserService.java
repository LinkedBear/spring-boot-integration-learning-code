package com.linkedbear.boot.j2cache.service;

import com.linkedbear.boot.j2cache.entity.User;
import com.linkedbear.boot.j2cache.mapper.UserMapper;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CachedUserService {
    
    @Autowired
    private CacheChannel cacheChannel;
    
    @Autowired
    private UserMapper userMapper;
    
    public User get(Integer id) {
        String cacheKey = "getUser." + id;
        CacheObject cacheObject = cacheChannel.get("default", cacheKey);
        if (cacheObject.getValue() != null) {
            return (User) cacheObject.getValue();
        }
        User user = userMapper.get(id);
        cacheChannel.set("default", cacheKey, user);
        return user;
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public List<User> findAllByName(String name) {
        return userMapper.findAllByNameLike(name);
    }
    
    public User update(User user) {
        userMapper.updateById(user);
        String cacheKey = "getUser." + user.getId();
        cacheChannel.set("default", cacheKey, user);
        return user;
    }
    
    public void deleteById(Integer id) {
//        userMapper.deleteById(id);
        System.out.println("deleteById invoke ......");
        String cacheKey = "getUser." + id;
        cacheChannel.evict("default", cacheKey);
    }
}
