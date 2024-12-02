package com.star.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull
    private Integer id; // 主键ID
    @NotEmpty  // validation非空校验
    private String categoryName; // 分类名称
    @NotEmpty  // validation非空校验
    private String categoryAlias; // 分类别名
    private Integer createUser; // 创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 日期格式化
    private LocalDateTime createTime; // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 日期格式化
    private LocalDateTime updateTime; // 更新时间
}
