package com.example.login_authetic.entity;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRole {
    @NotNull(message = "角色id不能为null")
    private Integer roleId;
    @Size(max = 20,min = 1,message = "角色名称应该不为空或者太长")
    private String role;

    public UserRole() {
    }

    public UserRole(Integer roleId, String phone, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
