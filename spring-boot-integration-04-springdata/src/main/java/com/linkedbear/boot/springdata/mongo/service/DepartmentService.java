package com.linkedbear.boot.springdata.mongo.service;

import com.linkedbear.boot.springdata.mongo.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Transactional(rollbackFor = Exception.class)
    public void save() {
        Department department = new Department();
        department.setName("dept");
        department.setTel("1234321");
        mongoTemplate.save(department);
    }
}
