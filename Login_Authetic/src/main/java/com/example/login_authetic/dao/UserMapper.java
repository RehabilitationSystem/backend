package com.example.login_authetic.dao;

import com.example.login_authetic.entity.User;
import com.example.login_authetic.entity.UserGender;
import com.example.login_authetic.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.Set;

@Mapper
public interface UserMapper {
    @Select("select * from user where  phone = #{phone}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "userId", column = "userId")
    })
    User getUserByPhone(String phone);

    @Select("select * from user where  userId = #{userId}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "userId", column = "userId")
    })
    User getUserByUseId(Long useId);

    @Insert("insert into user(userId,phone,username,password,roleId,age,introduce,gender) value(#{userId},#{phone},#{username},#{password},#{roleId},#{age},#{introduce},#{gender})")
    Integer insertUser(User user);

    @Insert("insert into url(url,roleId) value(#{url},#{roleId})")
    Integer insertUrl(String url,Integer roleId);

    @Insert("insert into userrole(roleId,role) value(#{roleId},#{role})")
    Integer insertRole(Integer roleId,String role);

    @Select("SELECT url.url  FROM url  WHERE EXISTS (SELECT 1 FROM userrole WHERE userrole.roleId = url.roleId AND EXISTS (SELECT 1 FROM user WHERE user.roleId = userrole.roleId AND user.userId = #{userId}))")
    Set<String> getPermissions(Long userId);

    @Update("update user set password = #{newPassword} where userId = #{userId}")
    Integer UpdatePassword(String newPassword,Long userId);

    @UpdateProvider(type = UserProvider.class, method = "updateModel")
    Integer UpdateInfo(User user);


    @Update("update user set roleId = #{roleId} where userId = #{userId}")
    Integer UpdatePermission(Integer roleId,Long userId);
}
