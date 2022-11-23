package com.linkedbear.boot.springdata.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doc_dept")
public class Department {
    
    @Id
    private String id;
    
    private String name;
    
    private String tel;
    
    @Override
    public String toString() {
        return "Department{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", tel='" + tel + '\'' + '}';
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
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
}
