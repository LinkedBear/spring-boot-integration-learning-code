package com.linkedbear.boot.security.jwt;

import org.springframework.security.core.userdetails.User;

import java.util.Date;

public class TokenPayload {
    
    private String id;
    
    private User user;
    
    private Date expiration;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Date getExpiration() {
        return expiration;
    }
    
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
}
