package com.star.pojo;


import lombok.Data;

import java.time.LocalDateTime;
// 使用Lombok，在编译阶段，为实体类自动生成setter、getter、toString方法
@Data
public class Article {
    private Integer id; // 主键ID
    private String title; // 文章标题
    private String content; // 文章内容
    private String coverImg; // 封面图像
    private String state; // 发布状态 已发布|草稿
    private Integer categoryId; // 文章分类id
    private Integer createUser; // 创建人ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
