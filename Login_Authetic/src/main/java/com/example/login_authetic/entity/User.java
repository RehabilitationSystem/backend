package com.example.login_authetic.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号格式错误")
    @NotNull(message = "电话号码不能为null")
    private String phone;

    @Size(max = 20,message = "用户名不能超过10个中文字符")
    @NotNull(message = "用户名不能为null",groups = RegisterGroup.class)
    @Size(min = 1,message = "用户名不能为空",groups = RegisterGroup.class)
    @Null(message = "登录时，用户名必须为空",groups = LoginGroup.class)
    private String username;

    @NotNull
    @Size(min = 8,message = "密码不能少于8个字符")
    @Size(max = 20,message = "密码不能超过20个字符")
    private String password;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    private Integer roleId;
    /* 省略get、set和带参构造方法 */



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
