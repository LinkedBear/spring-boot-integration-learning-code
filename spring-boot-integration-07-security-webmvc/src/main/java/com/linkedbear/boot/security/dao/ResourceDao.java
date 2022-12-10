package com.linkedbear.boot.security.dao;

import com.linkedbear.boot.security.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDao extends JpaRepository<Resource, Integer> {
    
}
