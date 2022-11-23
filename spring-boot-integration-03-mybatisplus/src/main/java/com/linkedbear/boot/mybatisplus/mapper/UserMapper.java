package com.linkedbear.boot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.linkedbear.boot.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    
    /**
     * 使用IPage作为入参和返回值
     * @param query
     * @return
     */
    IPage<User> page(IPage<User> query);
    
    /**
     * 使用集合作为返回值
     * @param query
     * @return
     */
    List<User> pageList(IPage<User> query);
    
    /**
     * 使用IPage和其他参数共同作为入参
     * @param page
     * @param params
     * @return
     */
    IPage<User> pageParams(@Param("page") IPage<User> page, @Param("params") Map<String, Object> params);
}
