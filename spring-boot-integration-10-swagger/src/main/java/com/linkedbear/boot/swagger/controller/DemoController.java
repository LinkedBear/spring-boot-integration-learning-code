package com.linkedbear.boot.swagger.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.linkedbear.boot.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "这是一个测试接口类")
@ApiSupport(author = "LinkedBear")
public class DemoController {
    
    @GetMapping("/test")
    @ApiOperation(value = "这是一个测试的接口", notes = "仅供测试，切勿当真")
    @ApiOperationSupport(author = "LinkedBear", order = 100)
    public String test(@ApiParam(value = "测试姓名", defaultValue = "zhangsan",
                                 allowableValues = "zhangsan,lisi,wangwu", required = true) String name,
            @ApiParam(value = "测试年龄", allowableValues = "10, 20, 30, 40, 50", example = "10") Integer age) {
        System.out.println(name);
        System.out.println(age);
        return "test";
    }
    
    @PostMapping(value = "/save")
    @ApiOperationSupport(author = "Baby", order = 1)
    public User save(User user) {
        System.out.println(user);
        return user;
    }
}
