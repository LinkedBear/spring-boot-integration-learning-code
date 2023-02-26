package com.linkedbear.boot.actuator.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class DemoController {
    
    @Autowired
    private MeterRegistry meterRegistry;
    
    private Counter counter;
    
    @PostConstruct
    public void init() {
        counter = meterRegistry.counter("demo.request");
    }
    
    @GetMapping("/demo")
    public String demo() {
        counter.increment();
        return "demo";
    }
}
