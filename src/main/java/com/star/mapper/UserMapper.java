package com.star.mapper;

import com.star.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Classname: UserMapper
 * @Date: 2024/11/26 18:48
 * @Author: 聂建强
 * @Description:
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);  // 根据用户名查询用户信息

    @Insert("insert into user (username, password, create_time, update_time) values (#{username}, #{password}, now(), now())")
    void register(String username, String password);  // 用户注册
}
