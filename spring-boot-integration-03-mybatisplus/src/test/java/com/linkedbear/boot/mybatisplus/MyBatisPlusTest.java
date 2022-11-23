package com.linkedbear.boot.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.linkedbear.boot.mybatisplus.entity.User;
import com.linkedbear.boot.mybatisplus.mapper.UserMapper;
import com.linkedbear.boot.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootTest
public class MyBatisPlusTest {
    
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
        userMapper.selectList(null).forEach(System.out::println);
        System.out.println("==================");
        userService.list().forEach(System.out::println);
        System.out.println(userService.count());
    }
    
    @Test
    public void test3() throws Exception {
        List<User> users = userMapper.selectList(Wrappers.<User>query().like("tel", "123"));
        users.forEach(System.out::println);
    }
    
    @Test
    public void test4() throws Exception {
        List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class).eq(User::getTel, "7654321"));
        users.forEach(System.out::println);
    }
    
    @Test
    public void test5() throws Exception {
        List<User> users = userMapper.selectList(Wrappers.lambdaQuery(User.class).select(User::getName));
        users.forEach(System.out::println);
    }
    
    @Test
    public void test6() throws Exception {
        User user = userMapper.selectOne(Wrappers.<User>query().select("max(id) as id"));
        System.out.println(user);
    }
    
    @Test
    public void test7() throws Exception {
    	User qo = new User();
    
        List<User> users = userMapper.selectList(
                Wrappers.lambdaQuery(User.class).like(StringUtils.hasText(qo.getName()), User::getName, qo.getName()));
        System.out.println(users.size());
        
        qo.setName("mybatisplus");
        users = userMapper.selectList(
                Wrappers.lambdaQuery(User.class).like(StringUtils.hasText(qo.getName()), User::getName, qo.getName()));
        System.out.println(users.size());
    }
}
