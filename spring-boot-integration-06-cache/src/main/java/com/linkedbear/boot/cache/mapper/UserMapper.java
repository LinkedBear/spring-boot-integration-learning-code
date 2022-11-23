package com.linkedbear.boot.cache.mapper;

import com.linkedbear.boot.cache.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    
    void save(User user);
    
    List<User> findAll();
    
    @Select("select * from tbl_user where id = #{value}")
    User get(Integer id);
    
    @Select("select * from tbl_user where name like concat('%', #{name}, '%')")
    List<User> findAllByNameLike(@Param("name") String name);
    
    @Delete("delete from tbl_user where id = #{id}")
    int deleteById(String id);
    
    @Update("CREATE TABLE tbl_role (\n"
                    + "  id int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  code varchar(20) NULL,\n"
                    + "  name varchar(32) NULL,\n"
                    + "  PRIMARY KEY (id)\n"
                    + ");")
    int excuteDDL();
    
    @Update("update tbl_user set name = #{name} where id = #{id}")
    void updateById(User user);
}
