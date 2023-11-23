package com.example.login_authetic.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;


public class Url {
    @Pattern(regexp = "^\\/[a-zA-Z]+\\/[0-9]+\\.[0-9]")
    @Size(min = 1,max = 30,message = "路由信息应该不为空或者太长")
    @NotNull(message = "路由信息不能为null")
    private String url;
//    如何判断外键的合法行
    @NotNull(message = "角色id不能为null")
    private Integer roleId;

    public Url(String url, Integer roleId) {
        this.url = url;
        this.roleId = roleId;
    }

    public Url() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
