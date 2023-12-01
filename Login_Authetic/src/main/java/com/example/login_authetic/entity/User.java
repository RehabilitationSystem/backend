package com.example.login_authetic.entity;


import com.example.commons.config.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.scanner.Constant;


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

    private Long userId;

    private Integer roleId = Constants.INITIAL_ROLE;

//    @Convert(converter = UserGender.Convert.class)
//    private UserGender gender = UserGender.UNKNOWN;
    private Integer gender = 3;


    private Byte age = Constants.INITIAL_AGE;

    @Size(min = 0,max = 255,message = "介绍不得为空，或者长度太长")
    private String introduce= Constants.INITIAL_INTRODUCE;



//    public UserGender getGender() {
//        return gender;
//    }
//
//    public void setGender(UserGender gender) {
//        this.gender = gender;
//    }


    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }




    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
