package com.example.login_authetic.entity;

import com.example.commons.config.Constants;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Size(max = 20,message = "用户名不能超过10个中文字符")
    @Size(min = 1,message = "用户名不能为空")
    private String username;

    @Max(value = 3)
    @Min(value = 0)
    @Convert(converter = UserGender.Convert.class)
    private UserGender gender = UserGender.UNKNOWN;

    private Byte age = Constants.INITIAL_AGE;
    @Size(min = 0,max = 255,message = "介绍不得为空，或者长度太长")
    private String introduce= Constants.INITIAL_INTRODUCE;



    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
