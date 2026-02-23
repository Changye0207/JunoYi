package com.junoyi.module.statistics.api.domain.vo;

import lombok.Data;

@Data
public class UserStatisticsVO {
    private Integer parentCount;
    private Integer organizerCount;
    private Integer studentCount;
    private Integer totalCount;
}
