package com.linkedbear.boot.j2cache.service;

import com.linkedbear.boot.j2cache.entity.User;
import com.linkedbear.boot.j2cache.mapper.UserMapper;
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
    
    @Cacheable(value = "j2cache.user.get", key = "#id")
    public User get(Integer id) {
        return userMapper.get(id);
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public List<User> findAllByName(String name) {
        return userMapper.findAllByNameLike(name);
    }
    
    @CachePut(value = "j2cache.user.get", key = "#user.id")
    public User update(User user) {
        userMapper.updateById(user);
        return user;
    }
    
    @CacheEvict(value = "j2cache.user.get", key = "#id")
    public void deleteById(Integer id) {
//        userMapper.deleteById(id);
        System.out.println("deleteById invoke ......");
    }
}
