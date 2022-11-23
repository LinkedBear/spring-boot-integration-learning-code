package com.linkedbear.boot.springdata;

import com.linkedbear.boot.springdata.jpa.dao.DepartmentDao;
import com.linkedbear.boot.springdata.jpa.dao.UserDao;
import com.linkedbear.boot.springdata.jpa.entity.Department;
import com.linkedbear.boot.springdata.jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {
    
    @Autowired
    private TransactionTemplate transactionTemplate;
    
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Test
    public void test1() throws Exception {
        List<User> users = userDao.findAll();
        System.out.println(users);
    }
    
    @Test
    public void test2() throws Exception {
        User user = new User();
        user.setName("test springdatajpa");
        user.setTel("123321");
        userDao.save(user);
    }
    
    @Test
    public void testSave() throws Exception {
        Department department = new Department();
        department.setName("测试部门");
        department.setAddress("test test");
        departmentDao.save(department);
    }
    
    @Test
    public void testUpdate() throws Exception {
        transactionTemplate.executeWithoutResult(status -> {
            Department department = departmentDao.getById(1);
            department.setName("测试修改部门");
            departmentDao.save(department);
        });
    }
    
    @Test
    public void testDelete() throws Exception {
    	transactionTemplate.executeWithoutResult(status -> {
    	    departmentDao.deleteById(1);
        });
    }
    
    @Test
    public void testQuery() throws Exception {
        List<Department> all = departmentDao.findAll();
        System.out.println(all);
        
        boolean exists = departmentDao.existsById(20);
        System.out.println(exists);
    
        Page<User> userPage = userDao.findAll(PageRequest.of(0, 2));
        System.out.println(userPage.getTotalElements());
        System.out.println(userPage.getTotalPages());
        System.out.println(userPage.getContent());
    }
    
    @Test
    public void testJPQL() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> userList = entityManager.createQuery("from User").getResultList();
        System.out.println(userList);
    
//        userDao.findAllByQuery();
        userDao.findAllById(1);
    }
    
    @Test
    public void testFind() throws Exception {
        User example = new User();
        example.setName("my");
        List<User> users = userDao.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> criterias = new ArrayList<>();
            criterias.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + example.getName() + "%"));
            return criteriaBuilder.and(criterias.toArray(new Predicate[0]));
        });
        users.forEach(System.out::println);
    }
}
