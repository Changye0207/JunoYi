package com.junoyi.module.appointment.api.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CheckinRecordVO {
    private Long id;
    private Long activityId;
    private Long enrollmentId;
    private Long studentId;
    private String checkinMethod;
    private String checkinCode;
    private String operatorId;
    private String operatorName;
    private String remark;
    private LocalDateTime createTime;
}
