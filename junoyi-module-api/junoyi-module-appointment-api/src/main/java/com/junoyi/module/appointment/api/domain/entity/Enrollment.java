package com.junoyi.module.appointment.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("enrollment")
public class Enrollment {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long appointmentId;
    private Long studentId;
    private Long activityId;
    private Integer checkinStatus;
    private LocalDateTime checkinTime;
    private String checkinMethod;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
