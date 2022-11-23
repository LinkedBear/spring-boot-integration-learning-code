package com.linkedbear.boot.springdata.mongo.dao;

import com.linkedbear.boot.springdata.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
    User findByName(String name);
    
    List<User> findAllByNameLike(String nameLike);
    
    List<User> findAllByAgeGreaterThan(Integer age);
    
    List<User> findAllByNameInOrderByAgeDesc(List<String> names);
}
