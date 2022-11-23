package com.linkedbear.boot.cache;

import com.linkedbear.boot.cache.entity.User;
import com.linkedbear.boot.cache.service.AnnotationUserService;
import com.linkedbear.boot.cache.service.CachedUserService;
import com.linkedbear.boot.cache.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CachedUserService cachedUserService;
    
    @Autowired
    private AnnotationUserService annotationUserService;
    
    @Test
    public void test1() throws Exception {
        System.out.println(userService.findAll());
        User user1 = userService.get(1);
        User user2 = userService.get(1);
        System.out.println(user1 == user2);
    }
    
    @Test
    public void test2() throws Exception {
        User user1 = cachedUserService.get(1);
        User user2 = cachedUserService.get(1);
        System.out.println(user1);
        System.out.println(user1 == user2);
    }
    
    @Test
    public void test3() throws Exception {
        User user1 = annotationUserService.get(6);
        User user2 = annotationUserService.get(6);
        System.out.println(user1 == user2);
    }
    
    @Test
    public void test4() throws Exception {
        User user = annotationUserService.get(1);
        user.setName("mybatis test");
        annotationUserService.update(user);
        User user2 = annotationUserService.get(1);
        System.out.println(user == user2);
    }
    
    @Test
    public void test5() throws Exception {
        User user1 = annotationUserService.get(1);
        User user2 = annotationUserService.get(1);
        annotationUserService.deleteById(1);
        User user3 = annotationUserService.get(1);
        System.out.println(user1 == user3);
    }
}
