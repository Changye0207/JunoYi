package com.junoyi.module.statistics.api.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StatisticsVO {
    private LocalDateTime date;
    private Integer userCount;
    private Integer activityCount;
    private Integer appointmentCount;
    private BigDecimal totalRevenue;
    private Integer activeUserCount;
}
