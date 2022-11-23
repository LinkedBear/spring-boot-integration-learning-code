package com.linkedbear.boot.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableMongoRepositories
@EnableTransactionManagement
public class SpringBootDataApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataApplication.class, args);
    }
}
