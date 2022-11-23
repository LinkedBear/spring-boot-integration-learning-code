package com.linkedbear.boot.cache;

import com.linkedbear.boot.cache.service.RedisCacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisCacheTest {
    
    @Autowired
    private RedisCacheService redisCacheService;
    
    @Test
    public void test1() throws Exception {
    	redisCacheService.getName(1);
    	redisCacheService.getName(1);
    	redisCacheService.getName(1);
    }
    
    @Test
    public void test2() throws Exception {
    	redisCacheService.getUser(1);
    	redisCacheService.getUser(1);
    	redisCacheService.getUser(1);
    }
}
