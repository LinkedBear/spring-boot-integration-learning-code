package com.linkedbear.boot.springdata.mongo.service;

import com.linkedbear.boot.springdata.mongo.dao.UserRepository;
import com.linkedbear.boot.springdata.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mongoUserService")
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional(rollbackFor = Exception.class)
    public void saveAndPrint() {
        User user = new User();
        user.setName("save");
        user.setAge(20);
        userRepository.save(user);
        
        int i = 1 / 0;
    
        System.out.println(userRepository.findAll());
    }
}
