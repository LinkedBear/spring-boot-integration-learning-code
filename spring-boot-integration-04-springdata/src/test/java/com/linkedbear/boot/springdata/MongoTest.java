package com.linkedbear.boot.springdata;

import com.linkedbear.boot.springdata.mongo.dao.UserRepository;
import com.linkedbear.boot.springdata.mongo.entity.Department;
import com.linkedbear.boot.springdata.mongo.entity.User;
import com.linkedbear.boot.springdata.mongo.service.DepartmentService;
import com.linkedbear.boot.springdata.mongo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;

//@DataMongoTest
@SpringBootTest
public class MongoTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Test
    public void test1() throws Exception {
        User user = new User();
        user.setName("mongo");
        user.setAge(20);
        userRepository.save(user);
    }
    
    @Test
    public void test2() throws Exception {
        List<User> users = userRepository.findAll();
        System.out.println(users);
    }
    
    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setName("template");
        user.setAge(12);
//    	mongoTemplate.save(user);
    
        User userWithId = mongoTemplate.insert(User.class).one(user);
        System.out.println(user);
        System.out.println(userWithId);
        System.out.println(user == userWithId);
    }
    
    @Test
    public void testUpdate() throws Exception {
        /*
        Optional<User> op = userRepository.findById("62f0eb78332a95603b13c35b");
        op.ifPresent(user -> {
            user.setAge(60);
            userRepository.save(user);
        });

        userRepository.findById("62f0eb78332a95603b13c35b").ifPresent(System.out::println);
         */
        
        // ---
      
        /*
        User replace = new User();
        replace.setAge(100);
        mongoTemplate.update(User.class).matching(Criteria.where("name").is("mongo")).replaceWith(replace).as(User.class).findAndReplaceValue();
        userRepository.findById("62f0eb78332a95603b13c35b").ifPresent(System.out::println);
         */
        
        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("mongo")), Update.update("age", 200), User.class);
        userRepository.findById("62f0eb78332a95603b13c35b").ifPresent(System.out::println);
    }
    
    @Test
    public void testDelete() throws Exception {
        userRepository.deleteById("62f0eb78332a95603b13c35b");
        userRepository.findById("62f0eb78332a95603b13c35b").ifPresent(System.out::println);
        
        mongoTemplate.remove(Query.query(Criteria.where("age").gt(30)), User.class);
        mongoTemplate.findAll(User.class).forEach(System.out::println);
    }
    
    @Test
    public void testQuery() throws Exception {
        System.out.println("userRepository.findAll(): " + userRepository.findAll());
        System.out.println("order by: " + userRepository.findAll(Sort.by(Sort.Order.desc("age"))));
        System.out.println("mongoTemplate.find: " + mongoTemplate.find(Query.query(Criteria.where("age").lt(18)), User.class));
        System.out.println("mongoTemplate.findById: " + mongoTemplate.findById("62f0f24e618cfd7eb146b14c", User.class));
    }
    
    @Test
    public void testUserService() throws Exception {
    	userService.saveAndPrint();
    }
    
    @Test
    public void testSaveDepartment() throws Exception {
        departmentService.save();
    }
}
