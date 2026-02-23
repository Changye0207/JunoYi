package com.junoyi.module.appointment.api.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EnrollmentVO {
    private Long id;
    private Long appointmentId;
    private Long studentId;
    private Long activityId;
    private Integer checkinStatus;
    private LocalDateTime checkinTime;
    private String checkinMethod;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
