package com.star.controller;

import com.star.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: ArticleController
 * @Date: 2024/11/26 20:05
 * @Author: 聂建强
 * @Description:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("所以文章的数据");
    }
}
