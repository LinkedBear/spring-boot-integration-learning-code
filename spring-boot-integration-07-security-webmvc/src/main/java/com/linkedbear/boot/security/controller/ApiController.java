package com.linkedbear.boot.security.controller;

import com.linkedbear.boot.security.entity.User;
import com.linkedbear.boot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/test")
    public String test() {
        return "test";
    }
    
    @GetMapping("/getUser")
    public String getUser(Integer id) {
        System.out.println("进入getUser方法！");
        return userService.get(id).getName();
    }
}
