package com.linkedbear.boot.springdata.jpa.dao;

import com.linkedbear.boot.springdata.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
    
}
