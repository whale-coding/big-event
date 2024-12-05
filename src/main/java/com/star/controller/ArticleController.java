package com.star.controller;

import com.star.common.Result;
import com.star.pojo.Article;
import com.star.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname: ArticleController
 * @Date: 2024/11/26 20:05
 * @Author: 聂建强
 * @Description:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
}
