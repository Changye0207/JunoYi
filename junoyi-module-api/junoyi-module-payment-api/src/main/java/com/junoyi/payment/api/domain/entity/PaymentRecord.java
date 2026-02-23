package com.junoyi.module.payment.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("payment_record")
public class PaymentRecord {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String paymentNo;
    private Long appointmentId;
    private Long parentId;
    private Long organizerId;
    private BigDecimal amount;
    private String paymentMethod;
    private Integer paymentStatus;
    private String transactionId;
    private String remark;
    private LocalDateTime paymentTime;
    private LocalDateTime refundTime;
    private String refundReason;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
