package com.junoyi.module.statistics.api.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 统计信息视图对象
 * 用于在API响应中返回每日统计数据，包含了用户、活动、预约和营收等数据
 */
@Data
public class StatisticsVO {
    /**
     * 统计日期
     */
    private LocalDateTime date;

    /**
     * 用户总数
     */
    private Integer userCount;

    /**
     * 活动总数
     */
    private Integer activityCount;

    /**
     * 预约总数
     */
    private Integer appointmentCount;

    /**
     * 总营收金额
     */
    private BigDecimal totalRevenue;

    /**
     * 活跃用户数
     */
    private Integer activeUserCount;
}
