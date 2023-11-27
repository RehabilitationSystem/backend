package com.example.login_authetic.dao;

import com.example.login_authetic.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where  phone = #{phone}")
    @Results({
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone")
    })
    User getUserByPhone(String phone);

    @Insert("insert into user(phone,username,password) value(#{phone},#{username},#{password})")
    Integer insertUser(String phone,String username,String password);
}
