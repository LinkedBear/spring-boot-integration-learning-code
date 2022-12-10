package com.linkedbear.boot.security.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用于解决User无法使用jackson反序列的问题
 */
public class SecurityUser extends User {
    
    @JsonCreator
    public SecurityUser(@JsonProperty("username") String username, @JsonProperty("password") String password,
            @JsonProperty("authorities") List<Map<String, String>> authorities) {
        super(username, "", authorities.stream().map(i -> new SimpleGrantedAuthority(i.get("authority"))).collect(Collectors.toList()));
    }
    
    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
