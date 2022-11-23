package com.linkedbear.boot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan
@EnableTransactionManagement
@SpringBootApplication
public class SpringBootMyBatisApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMyBatisApplication.class, args);
    }
}
