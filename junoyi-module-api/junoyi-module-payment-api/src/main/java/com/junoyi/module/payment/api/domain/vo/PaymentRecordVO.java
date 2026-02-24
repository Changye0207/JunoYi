package com.junoyi.module.payment.api.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付记录视图对象
 * 用于在API响应中返回支付记录的详细信息，
 * 包含了支付的基本属性和状态信息，作为数据传输对象
 */
@Data
public class PaymentRecordVO {
    /**
     * 支付记录ID
     */
    private Long id;

    /**
     * 支付单号，唯一标识符
     */
    private String paymentNo;

    /**
     * 预约ID，关联到预约表
     */
    private Long appointmentId;

    /**
     * 家长ID，关联到家长信息表
     */
    private Long parentId;

    /**
     * 组织者ID，关联到组织者信息表
     */
    private Long organizerId;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 支付方式（如：微信、支付宝、银行卡等）
     */
    private String paymentMethod;

    /**
     * 支付状态（0：待支付，1：已支付，2：已退款）
     */
    private Integer paymentStatus;

    /**
     * 交易ID，支付平台返回的交易标识
     */
    private String transactionId;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
