package com.junoyi.module.payment.api.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRecordVO {
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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
