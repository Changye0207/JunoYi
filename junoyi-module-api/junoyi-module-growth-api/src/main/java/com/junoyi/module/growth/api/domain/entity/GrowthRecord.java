package com.junoyi.module.growth.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("growth_record")
public class GrowthRecord {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long parentId;
    private Long studentId;
    private Long activityId;
    private String title;
    private String content;
    private String recordType;
    private Integer viewCount;
    private Integer likeCount;
    private Integer status;
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
