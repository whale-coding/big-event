package com.star.controller;

import com.star.common.Result;
import com.star.pojo.User;
import com.star.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: UserController
 * @Date: 2024/11/26 18:44
 * @Author: 聂建强
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Validated  // SpringBoot的参数校验，表示该类使用Validated参数校验
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        // TODO: 注册逻辑：先判断用户名是否已存在，再进行注册
        User u = userService.findUserByUsername(username);
        if (u == null){
            // 用户名不存在，可以进行注册
            userService.register(username, password);
            return Result.success();
        }else {
            // 用户名已经存在
            return Result.error("用户名已存在");
        }
    }
}
