package com.example.authetic_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
  
public class UserRegister {
  
    @JsonProperty("password")  
    private String password;  
  
    @JsonProperty("account")  
    private String account;  
  
    @JsonProperty("role")  
    private Integer role;  
  
    // 标准的getter和setter方法  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public String getAccount() {  
        return account;  
    }  
  
    public void setAccount(String account) {  
        this.account = account;  
    }  
  
    public Integer getRole() {  
        return role;  
    }  
  
    public void setRole(Integer role) {  
        this.role = role;  
    }  
}