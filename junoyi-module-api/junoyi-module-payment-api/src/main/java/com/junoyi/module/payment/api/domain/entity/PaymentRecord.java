package com.junoyi.module.payment.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付记录实体类
 * 对应数据库中的 payment_record 表，用于存储支付相关的记录信息
 * 是支付模块中的核心实体，包含了支付的所有基本属性和状态信息
 */
@Data
@TableName("payment_record")  // 数据库表名映射
public class PaymentRecord {
    /**
     * 支付记录ID，主键，自增
     */
    @TableId(type = IdType.AUTO)  // 主键策略：自增
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
     * 创建时间，自动填充
     */
    @TableField(fill = FieldFill.INSERT)  // 插入时自动填充
    private LocalDateTime createTime;

    /**
     * 更新时间，自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入和更新时自动填充
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标识（0：未删除，1：已删除）
     */
    @TableLogic  // 逻辑删除注解
    private Integer deleted;
}
