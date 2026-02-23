package com.junoyi.module.statistics.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.module.statistics.api.domain.vo.StatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.UserStatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.ActivityStatisticsVO;
import com.junoyi.module.statistics.api.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private IStatisticsService statisticsService;

    @RequiresPermissions("statistics:overview:list")
    @GetMapping("/overview")
    public R<StatisticsVO> getOverview() {
        return R.ok(statisticsService.getDailyStatistics(LocalDateTime.now()));
    }

    @RequiresPermissions("statistics:user:list")
    @GetMapping("/user")
    public R<UserStatisticsVO> getUserStatistics() {
        return R.ok(statisticsService.getUserStatistics());
    }

    @RequiresPermissions("statistics:activity:list")
    @GetMapping("/activity")
    public R<ActivityStatisticsVO> getActivityStatistics() {
        return R.ok(statisticsService.getActivityStatistics());
    }

    @RequiresPermissions("statistics:daily:list")
    @GetMapping("/daily/{date}")
    public R<StatisticsVO> getDailyStatistics(@PathVariable("date") String date) {
        // 这里需要解析日期字符串
        return R.ok(statisticsService.getDailyStatistics(LocalDateTime.now()));
    }

    @RequiresPermissions("statistics:recent7Days:list")
    @GetMapping("/recent7Days")
    public R<List<StatisticsVO>> getRecent7DaysStatistics() {
        return R.ok(statisticsService.getRecent7DaysStatistics());
    }

    @RequiresPermissions("statistics:recent30Days:list")
    @GetMapping("/recent30Days")
    public R<List<StatisticsVO>> getRecent30DaysStatistics() {
        return R.ok(statisticsService.getRecent30DaysStatistics());
    }
}
