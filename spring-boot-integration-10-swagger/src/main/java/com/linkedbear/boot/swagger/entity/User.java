package com.linkedbear.boot.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户类", description = "封装用户的基本信息")
public class User {
    
    @ApiModelProperty(value = "用户id", required = true)
    private String id;
    
    @ApiModelProperty(value = "用户姓名", required = true)
    private String name;
    
    @ApiModelProperty(value = "用户年龄", notes = "只能传入大于0的数")
    private Integer age;
    
    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", age=" + age + '}';
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
}
