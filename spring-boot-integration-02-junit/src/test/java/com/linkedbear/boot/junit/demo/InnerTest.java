package com.linkedbear.boot.junit.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InnerTest {
    
    @Test
    public void test3() throws Exception {
        System.out.println("Inner test run ......");
    }
}
