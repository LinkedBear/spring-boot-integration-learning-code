package com.linkedbear.boot.rocketmq.producer.dto;

public class UserDTO {
    
    private Integer userId;
    
    private String userName;
    
    private String sexName;
    
    @Override
    public String toString() {
        return "UserDTO{"
                + "userId="
                + userId
                + ", userName='"
                + userName
                + '\''
                + ", sexName='"
                + sexName
                + '\''
                + '}';
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getSexName() {
        return sexName;
    }
    
    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
}
