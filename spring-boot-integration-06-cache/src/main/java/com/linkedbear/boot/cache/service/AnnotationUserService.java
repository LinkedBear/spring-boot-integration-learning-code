package com.linkedbear.boot.cache.service;

import com.linkedbear.boot.cache.entity.User;
import com.linkedbear.boot.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnotationUserService {

    @Autowired
    private UserMapper userMapper;
    
//    @Cacheable(value = "user.get", key = "#root.caches[0]")
//    @Cacheable(value = "user.get", keyGenerator = "userKeyGenerator")
    @Cacheable(value = "user.get", key = "#id", condition = "#id % 2 == 1", unless = "#result.name.contains('jpa')")
    public User get(Integer id) {
        return userMapper.get(id);
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public List<User> findAllByName(String name) {
        return userMapper.findAllByNameLike(name);
    }
    
    @CachePut(value = "user.get", key = "#user.id")
    public User update(User user) {
        userMapper.updateById(user);
        return user;
    }
    
    @CacheEvict(value = "user.get", key = "#id")
    public void deleteById(Integer id) {
//        userMapper.deleteById(id);
        System.out.println("deleteById invoke ......");
    }
}
