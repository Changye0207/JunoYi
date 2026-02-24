package com.junoyi.statistics.api.service;

import com.junoyi.module.statistics.api.domain.vo.StatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.UserStatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.ActivityStatisticsVO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 统计服务接口
 * 定义统计相关的业务操作方法，包括查询每日统计、日期范围统计、用户统计、活动统计等
 * 提供各种维度的数据分析和统计功能
 */
public interface IStatisticsService {
    /**
     * 获取每日统计数据
     * 根据指定日期获取该日期的统计数据，包括用户、活动、预约和营收等信息
     * @param date 统计日期
     * @return 每日统计VO
     */
    StatisticsVO getDailyStatistics(LocalDateTime date);

    /**
     * 按日期范围查询统计数据
     * 根据指定的起始日期和结束日期查询该日期范围内的每日统计数据
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @return 统计数据列表
     */
    List<StatisticsVO> getStatisticsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * 获取用户统计数据
     * 获取平台用户相关的统计数据，包括用户总数、新增用户数、活跃用户数等
     * @return 用户统计VO
     */
    UserStatisticsVO getUserStatistics();

    /**
     * 获取活动统计数据
     * 获取平台活动相关的统计数据，包括活动总数、已发布活动数、参与人数等
     * @return 活动统计VO
     */
    ActivityStatisticsVO getActivityStatistics();

    /**
     * 获取最近7天的统计数据
     * 获取最近7天的每日统计数据，用于图表展示
     * @return 近7天统计数据列表
     */
    List<StatisticsVO> getRecent7DaysStatistics();

    /**
     * 获取最近30天的统计数据
     * 获取最近30天的每日统计数据，用于图表展示
     * @return 近30天统计数据列表
     */
    List<StatisticsVO> getRecent30DaysStatistics();
}
