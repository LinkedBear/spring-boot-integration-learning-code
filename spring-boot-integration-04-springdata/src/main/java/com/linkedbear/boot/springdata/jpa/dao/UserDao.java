package com.linkedbear.boot.springdata.jpa.dao;

import com.linkedbear.boot.springdata.jpa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    
    @Query("from User")
    List<User> findAllByQuery();
    
    @Query("from User where name like %?1%")
    List<User> findAllByNameLike(String name);
    
    @Query("from User where tel = :tel")
    List<User> findAllByTel(@Param("tel") String tel);
    
    @Query("from User where id in :ids")
    List<User> findAllByIds(@Param("ids") List<Integer> ids);
    
    @Query("from User where name like %?1%")
    List<User> findAllByNameLikePage(Pageable pageable, String name);
    
    List<User> findAllById(Integer id);
}
