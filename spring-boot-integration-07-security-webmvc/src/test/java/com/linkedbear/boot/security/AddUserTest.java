package com.linkedbear.boot.security;

import com.linkedbear.boot.security.dao.UserDao;
import com.linkedbear.boot.security.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AddUserTest {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUsername("xiaoshuai");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setName("小帅");
        user.setTel("123456789");
        userDao.save(user);
        
        user = new User();
        user.setUsername("xiaoming");
        user.setPassword(passwordEncoder.encode("654321"));
        user.setName("小明");
        user.setTel("987654321");
        userDao.save(user);
        
        user = new User();
        user.setUsername("boss");
        user.setPassword(passwordEncoder.encode("111111"));
        user.setName("老板");
        user.setTel("147852369");
        userDao.save(user);
    }
}
