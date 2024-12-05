package com.star.service.impl;

import com.star.mapper.ArticleMapper;
import com.star.pojo.Article;
import com.star.service.ArticleService;
import com.star.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Classname: ArticleServiceImpl
 * @Date: 2024/12/2 18:43
 * @Author: 聂建强
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    // 新增文章
    @Override
    public void add(Article article) {
        // 从ThreadLocal中获取载荷，即用户信息
        Map<String, Object> claims = ThreadLocalUtil.get();
        // 从载荷中获取登录用户的id
        Integer user_id = (Integer) claims.get("id");
        // 补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateUser(user_id);

        articleMapper.add(article);
    }
}
