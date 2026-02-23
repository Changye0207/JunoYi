package com.junoyi.module.user.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("organizer_info")
public class OrganizerInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private String name;
    private String contactPerson;
    private String contactPhone;
    private String address;
    private String businessLicense;
    private String description;
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
