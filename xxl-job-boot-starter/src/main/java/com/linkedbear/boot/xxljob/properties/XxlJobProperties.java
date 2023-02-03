package com.linkedbear.boot.xxljob.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {
    
    private String adminAddresses;
    
    private String accessToken = "default_token";
    
    private String appname;
    
    private String address;
    
    private String ip;
    
    private int port = 9999;
    
    private String logPath;
    
    private int logRetentionDays = 30;
    
    public String getAdminAddresses() {
        return adminAddresses;
    }
    
    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getAppname() {
        return appname;
    }
    
    public void setAppname(String appname) {
        this.appname = appname;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public String getLogPath() {
        return logPath;
    }
    
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
    
    public int getLogRetentionDays() {
        return logRetentionDays;
    }
    
    public void setLogRetentionDays(int logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }
}
