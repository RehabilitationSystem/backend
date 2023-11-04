package com.example.newsystem01.dao;

import com.example.newsystem01.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {


    @Insert("insert into user (user_name, password) values (#{username}, #{password})")
    Integer insertUser(User user);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "id", column = "id")
    })
    User getUser(int id);

    @Select("select * from user where id = #{id} and user_name=#{name}")
    @Results({
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "password"),
            @Result(property = "id", column = "id")
    })
    User getUserByIdAndName(@Param("id") Long id, @Param("name") String username);
}
