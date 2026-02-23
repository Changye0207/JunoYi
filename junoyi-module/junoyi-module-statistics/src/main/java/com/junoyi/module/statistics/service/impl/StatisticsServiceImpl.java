package com.junoyi.module.statistics.service.impl;

import com.junoyi.module.statistics.api.domain.vo.StatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.UserStatisticsVO;
import com.junoyi.module.statistics.api.domain.vo.ActivityStatisticsVO;
import com.junoyi.module.statistics.api.service.IStatisticsService;
import com.junoyi.module.user.api.domain.entity.ParentInfo;
import com.junoyi.module.user.api.domain.entity.OrganizerInfo;
import com.junoyi.module.user.api.domain.entity.StudentInfo;
import com.junoyi.module.user.api.service.IParentInfoService;
import com.junoyi.module.user.api.service.IOrganizerInfoService;
import com.junoyi.module.user.api.service.IStudentInfoService;
import com.junoyi.module.activity.api.domain.entity.Activity;
import com.junoyi.module.activity.api.service.IActivityService;
import com.junoyi.module.appointment.api.domain.entity.Appointment;
import com.junoyi.module.appointment.api.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private IParentInfoService parentInfoService;

    @Autowired
    private IOrganizerInfoService organizerInfoService;

    @Autowired
    private IStudentInfoService studentInfoService;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IAppointmentService appointmentService;

    @Override
    public StatisticsVO getDailyStatistics(LocalDateTime date) {
        StatisticsVO vo = new StatisticsVO();
        vo.setDate(date);
        vo.setUserCount(getUserStatistics().getTotalCount());
        vo.setActivityCount((int) activityService.count());
        vo.setAppointmentCount((int) appointmentService.count());
        vo.setTotalRevenue(calculateTotalRevenue());
        vo.setActiveUserCount(calculateActiveUserCount());
        return vo;
    }

    @Override
    public List<StatisticsVO> getStatisticsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<StatisticsVO> result = new ArrayList<>();
        // 这里可以实现按日期范围查询统计数据的逻辑
        // 为了简单起见，现在只返回一个空列表
        return result;
    }

    @Override
    public UserStatisticsVO getUserStatistics() {
        UserStatisticsVO vo = new UserStatisticsVO();
        vo.setParentCount((int) parentInfoService.count());
        vo.setOrganizerCount((int) organizerInfoService.count());
        vo.setStudentCount((int) studentInfoService.count());
        vo.setTotalCount(vo.getParentCount() + vo.getOrganizerCount() + vo.getStudentCount());
        return vo;
    }

    @Override
    public ActivityStatisticsVO getActivityStatistics() {
        ActivityStatisticsVO vo = new ActivityStatisticsVO();
        vo.setTotalCount((int) activityService.count());
        vo.setPublishedCount((int) activityService.count(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Activity>()
                .eq(Activity::getStatus, 1)));
        vo.setOfflineCount((int) activityService.count(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Activity>()
                .eq(Activity::getStatus, 2)));
        vo.setPendingCount((int) activityService.count(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Activity>()
                .eq(Activity::getStatus, 0)));
        vo.setAveragePrice(calculateAverageActivityPrice());
        vo.setTotalParticipants(calculateTotalParticipants());
        return vo;
    }

    @Override
    public List<StatisticsVO> getRecent7DaysStatistics() {
        List<StatisticsVO> result = new ArrayList<>();
        // 这里可以实现获取最近7天统计数据的逻辑
        // 为了简单起见，现在只返回一个空列表
        return result;
    }

    @Override
    public List<StatisticsVO> getRecent30DaysStatistics() {
        List<StatisticsVO> result = new ArrayList<>();
        // 这里可以实现获取最近30天统计数据的逻辑
        // 为了简单起见，现在只返回一个空列表
        return result;
    }

    private BigDecimal calculateTotalRevenue() {
        BigDecimal totalRevenue = BigDecimal.ZERO;
        List<Appointment> appointments = appointmentService.list(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Appointment>()
                .eq(Appointment::getPayStatus, 1)); // 支付成功
        for (Appointment appointment : appointments) {
            if (appointment.getTotalAmount() != null) {
                totalRevenue = totalRevenue.add(appointment.getTotalAmount());
            }
        }
        return totalRevenue;
    }

    private int calculateActiveUserCount() {
        // 这里可以实现计算活跃用户数的逻辑
        // 为了简单起见，现在返回总用户数的50%作为活跃用户数
        return getUserStatistics().getTotalCount() / 2;
    }

    private BigDecimal calculateAverageActivityPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int count = 0;
        List<Activity> activities = activityService.list();
        for (Activity activity : activities) {
            if (activity.getPrice() != null) {
                totalPrice = totalPrice.add(activity.getPrice());
                count++;
            }
        }
        if (count > 0) {
            return totalPrice.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    private int calculateTotalParticipants() {
        int totalParticipants = 0;
        List<Activity> activities = activityService.list();
        for (Activity activity : activities) {
            if (activity.getCurrentParticipants() != null) {
                totalParticipants += activity.getCurrentParticipants();
            }
        }
        return totalParticipants;
    }
}
