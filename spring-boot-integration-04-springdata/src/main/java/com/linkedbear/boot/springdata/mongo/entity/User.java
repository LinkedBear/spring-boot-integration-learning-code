package com.linkedbear.boot.springdata.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "doc_user")
public class User {
    
    @Id
    private String id;
    
    @Field("username")
    private String name;
    
    private Integer age;
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
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
