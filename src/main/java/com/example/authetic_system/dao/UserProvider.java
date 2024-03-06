package com.example.authetic_system.dao;



import com.example.authetic_system.entity.hide.User;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private String tableName = "user";

    public String updateModel(User user) {
        return new SQL() {{
            UPDATE(tableName);
            if (user.getUsername()!=null&&user.getUsername()!="") {
                SET(String.join(".", tableName, "username = #{username}"));
            }
            if (user.getAge()!=null&&user.getAge()>0) {
                SET(String.join(".", tableName, "age = #{age}"));
            }
            if (user.getGender()!=null) {
                SET(String.join(".", tableName, "gender = #{gender}"));
            }
            if (user.getIntroduce()!=null&&user.getIntroduce()!="") {
                SET(String.join(".", tableName, "introduce = #{introduce}"));
            }
            if (user.getAvatar()!=null&&user.getAvatar()!="") {
                SET(String.join(".", tableName, "avatar = #{avatar}"));
            }
            WHERE("userId=#{userId}");
        }}.toString();
    }


}

