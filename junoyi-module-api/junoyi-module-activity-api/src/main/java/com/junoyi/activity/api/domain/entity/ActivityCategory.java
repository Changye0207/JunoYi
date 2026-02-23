package com.junoyi.module.activity.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("activity_category")
public class ActivityCategory {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private String code;
    private String icon;
    private Integer sort;
    private Integer status;
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
