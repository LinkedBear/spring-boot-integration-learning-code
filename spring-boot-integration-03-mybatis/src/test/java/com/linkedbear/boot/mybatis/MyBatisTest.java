package com.linkedbear.boot.mybatis;

import com.linkedbear.boot.mybatis.entity.User;
import com.linkedbear.boot.mybatis.mapper.UserMapper;
import com.linkedbear.boot.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisTest {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserService userService;
    
    @Test
    public void test1() {
        userService.test();
    }
    
    @Test
    public void test2() {
        List<User> users = userMapper.findAll();
        System.out.println(users);
    }
    
    @Test
    public void test3() throws Exception {
    	userMapper.excuteDDL();
    }
}
