package com.junoyi.module.appointment.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("checkin_record")
public class CheckinRecord {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long activityId;
    private Long enrollmentId;
    private Long studentId;
    private String checkinMethod;
    private String checkinCode;
    private String operatorId;
    private String operatorName;
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    private Integer deleted;
}
