package com.junoyi.module.statistics.api.service;

import com.junoyi.module.statistics.api.domain.vo.StatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.UserStatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.ActivityStatisticsVO;
import java.time.LocalDateTime;
import java.util.List;

public interface IStatisticsService {
    StatisticsVO getDailyStatistics(LocalDateTime date);
    List<StatisticsVO> getStatisticsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    UserStatisticsVO getUserStatistics();
    ActivityStatisticsVO getActivityStatistics();
    List<StatisticsVO> getRecent7DaysStatistics();
    List<StatisticsVO> getRecent30DaysStatistics();
}
