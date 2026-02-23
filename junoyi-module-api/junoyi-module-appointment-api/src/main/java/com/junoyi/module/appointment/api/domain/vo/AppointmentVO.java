package com.junoyi.module.appointment.api.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AppointmentVO {
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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
