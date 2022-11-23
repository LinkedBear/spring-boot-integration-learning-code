package com.linkedbear.boot.springdata.jpa.service;

import com.linkedbear.boot.springdata.jpa.dao.UserDao;
import com.linkedbear.boot.springdata.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        User user = new User();
        user.setName("test mybatis");
        user.setTel("7654321");
        userDao.save(user);
    
//        int i = 1 / 0;
        
        List<User> userList = userDao.findAll();
        userList.forEach(System.out::println);
    }
}
