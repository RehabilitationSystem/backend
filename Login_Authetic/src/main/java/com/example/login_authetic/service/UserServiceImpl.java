package com.example.login_authetic.service;

import com.example.commons.exceptiondeal.BusinessErrorException;
import com.example.commons.exceptiondeal.BusinessMsgEnum;
import com.example.login_authetic.dao.UserMapper;
import com.example.login_authetic.entity.User;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    /**
     *  根据电话号码查询用户，并且进行密码匹配。
     * @param input 输入的待验证用户对象
     * @return
     */
    @Override
    public User login(User input) {
        User user;
        if((user=userMapper.getUserByPhone(input.getPhone()))==null){
            throw new BusinessErrorException(BusinessMsgEnum.USER_NOT_EXISTED);
        }
        if(!user.getPassword().equals(input.getPassword())){
            throw new BusinessErrorException(BusinessMsgEnum.PASSWORD_WRONG_EXISTED);
        }
//        密码尽量不传输
        user.setPassword(null);
        return user;
    }

    /**
     * 判断电话号码是否被注册，插入用户数据到数据库
      @param input 输入的待验证用户对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User input) {
        if(userMapper.getUserByPhone(input.getPhone())!=null){
            throw new BusinessErrorException(BusinessMsgEnum.USER_IS_EXISTED);
        }
        if(userMapper.insertUser(input.getPhone(),input.getUsername(),input.getPassword())==0){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }

    }

    @Override
    public Set<String> getRoles(String username) {
        return null;
    }

    @Override
    public Set<String> getPermissions(String username) {
        return null;
    }

    @Override
    public User getByPhone(String phone) {

        return null;
    }


}
