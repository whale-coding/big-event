package com.star.controller;

import com.star.common.Result;
import com.star.pojo.User;
import com.star.service.UserService;
import com.star.utils.JwtUtil;
import com.star.utils.Md5Util;
import com.star.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 用户注册接口
     * @param username 用户名
     * @param password 密码
     * @return 是否注册成功
     */
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

    /**
     * 用户登录接口
     * @param username 用户名
     * @param password 密码
     * @return 是否登录成功，成功则返回token令牌
     */
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        // 1、根据用户名查询用户信息
        User loginUser = userService.findUserByUsername(username);
        // 2.判断用户是否存在
        if (loginUser == null){
            return Result.error("用户信息不存在，请注册");
        }
        // 3.判断密码是否正确,注意loginUser中的密码是密文
        if(Md5Util.checkPassword(password, loginUser.getPassword())){
            // 密码校验成功，生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            // 返回token
            return Result.success(token);
        }
        // 密码校验失败
        return Result.error("用户名或密码错误，请重新输入");
    }

    /**
     * 获取登录用户的详细信息
     // * @param token 请求头中的token
     * @return 用户的信息
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name ="Authorization") String token*/){
        // 解析token
        // Map<String, Object> claims = JwtUtil.parseToken(token);
        // 从解析后的载荷中获取用户名
        // String username = (String) claims.get("username");

        // 从ThreadLocal中获取载荷，即用户信息
        Map<String, Object> claims = ThreadLocalUtil.get();
        // 从载荷中获取用户名
        String username = (String) claims.get("username");
        // 根据用户名查询用户
        User user = userService.findUserByUsername(username);
        // 返回用户信息
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * @param user 要更新的用户
     * @return 是否更新成功
     * 注解@RequestBody的作用：将前端传过来的json字符串转换为一个实体类对象
     * 注解@PutMapping常用于update方法。
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
}
