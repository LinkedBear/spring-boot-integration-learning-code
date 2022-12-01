package com.linkedbear.boot.j2cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootJ2cacheApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJ2cacheApplication.class, args);
    }
}
