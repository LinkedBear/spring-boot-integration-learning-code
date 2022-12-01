package com.linkedbear.boot.j2cache.service;

import com.linkedbear.boot.j2cache.entity.User;
import com.linkedbear.boot.j2cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    public User get(Integer id) {
        return userMapper.get(id);
    }
    
    public List<User> findAll() {
        return userMapper.findAll();
    }
    
    public List<User> findAllByName(String name) {
        return userMapper.findAllByNameLike(name);
    }
}
