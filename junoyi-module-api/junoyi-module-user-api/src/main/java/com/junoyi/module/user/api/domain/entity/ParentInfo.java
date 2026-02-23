package com.junoyi.module.user.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("parent_info")
public class ParentInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private String realName;
    private String phone;
    private String idCard;
    private String avatar;
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
