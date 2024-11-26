package com.star.service;

import com.star.pojo.User;

/**
 * @Classname: UserService
 * @Date: 2024/11/26 18:48
 * @Author: 聂建强
 * @Description:
 */
public interface UserService {
    // 根据用户名查询用户信息
    User findUserByUsername(String username);
    // 注册
    void register(String username, String password);
}
