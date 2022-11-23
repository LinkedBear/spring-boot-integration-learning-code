package com.linkedbear.boot.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan
@EnableTransactionManagement
@SpringBootApplication
public class SpringBootMyBatisPlusApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMyBatisPlusApplication.class, args);
    }
}
