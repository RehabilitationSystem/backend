package com.example.login_authetic.dao;



import com.example.login_authetic.entity.User;
import com.example.login_authetic.entity.UserInfo;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;

public class UserUpdateProvider {
    private String tableName = "user";

    public String updateModel(UserInfo userInfo) {
        return new SQL() {{
            UPDATE(tableName);
            if (userInfo.getUsername()!=null) {
                SET(String.join(".", tableName, "username = #{userInfo.username}"));
            }
            if (userInfo.getAge()!=null) {
                SET(String.join(".", tableName, "age = #{userInfo.age}"));
            }
            if (userInfo.getGender()!=null) {
                SET(String.join(".", tableName, "gender = #{userInfo.userGender}"));
            }
            if (userInfo.getIntroduce()!=null) {
                SET(String.join(".", tableName, "gender = #{userInfo.introduce}"));
            }
            WHERE("userId=#{userId}");
        }}.toString();
    }
}

