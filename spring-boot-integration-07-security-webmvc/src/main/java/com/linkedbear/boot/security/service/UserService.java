package com.linkedbear.boot.security.service;

import com.linkedbear.boot.security.dao.UserDao;
import com.linkedbear.boot.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userDao.findAllByUsername(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("用户名" + username + "不存在！");
        }
        User user = users.get(0);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername()).password(user.getPassword());
        if (StringUtils.hasText(user.getRoles())) {
            userBuilder = userBuilder.roles(user.getRoles().split(","));
        }
        if (StringUtils.hasText(user.getResources())) {
            userBuilder = userBuilder.authorities(user.getResources().split(","));
        }
        return userBuilder.build();
    }
    
    @PreAuthorize("hasAuthority('getUser')")
    public User get(Integer id) {
        System.out.println("进入UserService的get方法！");
        return userDao.getById(id);
    }
}
