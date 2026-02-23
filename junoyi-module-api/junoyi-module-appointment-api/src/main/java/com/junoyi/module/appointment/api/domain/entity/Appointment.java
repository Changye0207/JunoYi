package com.junoyi.module.appointment.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("appointment")
public class Appointment {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderNo;
    private Long parentId;
    private Long activityId;
    private Long organizerId;
    private Integer studentCount;
    private BigDecimal totalAmount;
    private Integer payStatus;
    private Integer orderStatus;
    private String remark;
    private LocalDateTime payTime;
    private LocalDateTime cancelTime;
    private String cancelReason;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
