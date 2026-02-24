package com.junoyi.module.statistics.api.domain.vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ActivityStatisticsVO {
    private Integer totalCount;
    private Integer publishedCount;
    private Integer offlineCount;
    private Integer pendingCount;
    private BigDecimal averagePrice;
    private Integer totalParticipants;
}
