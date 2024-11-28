package com.star.service.impl;

import com.star.mapper.UserMapper;
import com.star.pojo.User;
import com.star.service.UserService;
import com.star.utils.Md5Util;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    // 根据用户名查询用户信息
    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    // 注册
    @Override
    public void register(String username, String password) {
        // 对密码进行加密
        password = Md5Util.getMD5String(password);
        // 注册
        userMapper.register(username, password);

    }

    // 更新用户信息
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());  // 设置更新时间
        // 更新用户信息
        userMapper.update(user);
    }
}
