package com.example.login_authetic.dao;



import com.example.login_authetic.entity.User;
import com.example.login_authetic.entity.UserInfo;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private String tableName = "user";

    public String updateModel(User user) {
        return new SQL() {{
            UPDATE(tableName);
            if (user.getUsername()!=null) {
                SET(String.join(".", tableName, "username = #{username}"));
            }
            if (user.getAge()!=null) {
                SET(String.join(".", tableName, "age = #{age}"));
            }
            if (user.getGender()!=null) {
                SET(String.join(".", tableName, "gender = #{gender}"));
            }
            if (user.getIntroduce()!=null) {
                SET(String.join(".", tableName, "introduce = #{introduce}"));
            }
            if (user.getAvatar()!=null) {
                SET(String.join(".", tableName, "avatar = #{avatar}"));
            }
            WHERE("userId=#{userId}");
        }}.toString();
    }


}

