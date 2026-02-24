package com.junoyi.module.activity.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 活动实体类
 * 对应数据库中的 activity 表，用于存储活动的基本信息
 * 是活动模块中的核心实体，包含了活动的所有基本属性和配置信息
 */
@Data
@TableName("activity")  // 数据库表名映射
public class Activity {
    /**
     * 活动ID，主键，自增
     */
    @TableId(type = IdType.AUTO)  // 主键策略：自增
    private Long id;

    /**
     * 组织者ID，关联到组织者信息表
     */
    private Long organizerId;

    /**
     * 活动分类ID，关联到活动分类表
     */
    private Long categoryId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动封面图片URL
     */
    private String coverImage;

    /**
     * 活动摘要描述
     */
    private String summary;

    /**
     * 活动详细内容
     */
    private String content;

    /**
     * 活动类型（如：线上、线下、混合）
     */
    private String activityType;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 活动经度坐标
     */
    private BigDecimal longitude;

    /**
     * 活动纬度坐标
     */
    private BigDecimal latitude;

    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 报名截止时间
     */
    private LocalDateTime signUpDeadline;

    /**
     * 最大参与人数
     */
    private Integer maxParticipants;

    /**
     * 当前参与人数
     */
    private Integer currentParticipants;

    /**
     * 活动价格
     */
    private BigDecimal price;

    /**
     * 年龄范围限制
     */
    private String ageRange;

    /**
     * 难度级别
     */
    private String difficultyLevel;

    /**
     * 活动状态（0：草稿，1：待审核，2：已发布，3：已取消）
     */
    private Integer status;

    /**
     * 活动浏览次数
     */
    private Integer viewCount;

    /**
     * 活动备注信息
     */
    private String remarks;

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
