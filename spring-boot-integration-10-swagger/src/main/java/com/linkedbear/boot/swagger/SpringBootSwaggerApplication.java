package com.linkedbear.boot.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKnife4j
public class SpringBootSwaggerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSwaggerApplication.class, args);
    }
}
