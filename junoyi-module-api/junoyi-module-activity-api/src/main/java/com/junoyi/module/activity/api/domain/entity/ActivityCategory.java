package com.junoyi.module.activity.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 活动分类实体类
 * 对应数据库中的 activity_category 表，用于存储活动分类信息
 * 是活动模块中的重要实体，用于对活动进行分类管理
 */
@Data
@TableName("activity_category")  // 数据库表名映射
public class ActivityCategory {
    /**
     * 活动分类ID，主键，自增
     */
    @TableId(type = IdType.AUTO)  // 主键策略：自增
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类编码
     */
    private String code;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 排序顺序
     */
    private Integer sort;

    /**
     * 分类状态（0：禁用，1：正常）
     */
    private Integer status;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 创建时间，自动填充
     */
    @TableField(fill = FieldFill.INSERT)  // 插入时自动填充
    private LocalDateTime createTime;

    /**
     * 更新时间，自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入和更新时自动填充
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标识（0：未删除，1：已删除）
     */
    @TableLogic  // 逻辑删除注解
    private Integer deleted;
}
