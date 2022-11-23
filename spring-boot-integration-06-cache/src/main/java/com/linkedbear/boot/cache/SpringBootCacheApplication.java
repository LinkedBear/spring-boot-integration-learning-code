package com.linkedbear.boot.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootCacheApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }
}
