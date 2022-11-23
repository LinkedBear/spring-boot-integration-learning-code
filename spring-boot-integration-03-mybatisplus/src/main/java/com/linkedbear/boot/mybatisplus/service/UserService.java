package com.linkedbear.boot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linkedbear.boot.mybatisplus.entity.User;
import com.linkedbear.boot.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    
    @Autowired
    private UserMapper userMapper;
    
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        User user = new User();
        user.setName("test mybatisplus");
        user.setTel("7654321");
        userMapper.insert(user);
    
//        int i = 1 / 0;
        
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
