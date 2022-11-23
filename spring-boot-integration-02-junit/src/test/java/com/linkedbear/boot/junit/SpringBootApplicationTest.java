package com.linkedbear.boot.junit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class SpringBootApplicationTest {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Test
    public void test1() {
        System.out.println("SpringBootApplicationTest test1 run ......");
    }
    
    @Test
    public void test2() {
        System.out.println(restTemplate);
    }
}
