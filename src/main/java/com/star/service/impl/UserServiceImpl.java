package com.star.service.impl;

import com.star.mapper.UserMapper;
import com.star.pojo.User;
import com.star.service.UserService;
import com.star.utils.Md5Util;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Classname: UserServiceImpl
 * @Date: 2024/11/26 18:49
 * @Author: 聂建强
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        // 对密码进行加密
        password = Md5Util.getMD5String(password);
        // 注册
        userMapper.register(username, password);

    }
}
