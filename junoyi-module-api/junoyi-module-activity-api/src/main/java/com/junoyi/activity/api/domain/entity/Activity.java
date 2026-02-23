package com.junoyi.module.activity.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("activity")
public class Activity {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long organizerId;
    private Long categoryId;
    private String title;
    private String coverImage;
    private String summary;
    private String content;
    private String activityType;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime signUpDeadline;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private BigDecimal price;
    private String ageRange;
    private String difficultyLevel;
    private Integer status;
    private Integer viewCount;
    private String remarks;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
