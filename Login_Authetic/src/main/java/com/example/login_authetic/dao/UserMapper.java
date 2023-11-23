package com.example.login_authetic.dao;

import com.example.login_authetic.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Set;

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

    @Insert("insert into url(url,roleId) value(#{url},#{roleId})")
    Integer insertUrl(String url,Integer roleId);

    @Insert("insert into userrole(roleId,role) value(#{roleId},#{role})")
    Integer insertRole(Integer roleId,String role);

    @Select("SELECT url.url  FROM url  WHERE EXISTS (SELECT 1 FROM userrole WHERE userrole.roleId = url.roleId AND EXISTS (SELECT 1 FROM user WHERE user.roleId = userrole.roleId AND user.phone = #{phone}))")
    Set<String> getPermissions(String phone);


}
