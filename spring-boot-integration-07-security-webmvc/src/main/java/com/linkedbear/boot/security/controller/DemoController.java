package com.linkedbear.boot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }
    
    @GetMapping("/getData")
    public String getData(String name) {
        return "userData-" + name;
    }
}
