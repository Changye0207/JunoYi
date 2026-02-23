package com.junoyi.module.appointment.service.impl;

import com.junoyi.module.activity.api.domain.entity.Activity;
import com.junoyi.module.activity.api.service.IActivityService;
import com.junoyi.module.appointment.api.domain.entity.Appointment;
import com.junoyi.module.appointment.api.domain.entity.Enrollment;
import com.junoyi.module.appointment.api.domain.vo.AppointmentVO;
import com.junoyi.module.appointment.api.mapper.AppointmentMapper;
import com.junoyi.module.appointment.api.service.IAppointmentService;
import com.junoyi.module.appointment.api.service.IEnrollmentService;
import com.junoyi.module.appointment.convert.AppointmentConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment>
        implements IAppointmentService {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IEnrollmentService enrollmentService;

    @Override
    public List<AppointmentVO> selectAppointmentList(Appointment appointment) {
        LambdaQueryWrapper<Appointment> wrapper = new LambdaQueryWrapper<>();
        if (appointment.getParentId() != null) {
            wrapper.eq(Appointment::getParentId, appointment.getParentId());
        }
        if (appointment.getActivityId() != null) {
            wrapper.eq(Appointment::getActivityId, appointment.getActivityId());
        }
        if (appointment.getOrderStatus() != null) {
            wrapper.eq(Appointment::getOrderStatus, appointment.getOrderStatus());
        }
        wrapper.orderByDesc(Appointment::getCreateTime);
        List<Appointment> list = list(wrapper);
        return AppointmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    public AppointmentVO selectAppointmentById(Long id) {
        Appointment appointment = getById(id);
        return AppointmentConvert.INSTANCE.toVO(appointment);
    }

    @Override
    public List<AppointmentVO> selectAppointmentListByParentId(Long parentId) {
        LambdaQueryWrapper<Appointment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Appointment::getParentId, parentId);
        wrapper.orderByDesc(Appointment::getCreateTime);
        List<Appointment> list = list(wrapper);
        return AppointmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    public List<AppointmentVO> selectAppointmentListByOrganizerId(Long organizerId) {
        LambdaQueryWrapper<Appointment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Appointment::getOrganizerId, organizerId);
        wrapper.orderByDesc(Appointment::getCreateTime);
        List<Appointment> list = list(wrapper);
        return AppointmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Appointment createAppointment(Appointment appointment, List<Long> studentIds) {
        Activity activity = activityService.getById(appointment.getActivityId());

        if (!activityService.checkActivityAvailable(appointment.getActivityId())) {
            throw new RuntimeException("活动不可预约");
        }

        if (!activityService.incrementParticipants(appointment.getActivityId())) {
            throw new RuntimeException("预约失败，名额不足");
        }

        try {
            appointment.setOrderNo(generateOrderNo());
            appointment.setStudentCount(studentIds.size());
            appointment.setTotalAmount(activity.getPrice().multiply(new java.math.BigDecimal(studentIds.size())));
            appointment.setPayStatus(0);
            appointment.setOrderStatus(1);
            appointment.setOrganizerId(activity.getOrganizerId());

            save(appointment);

            for (Long studentId : studentIds) {
                Enrollment enrollment = new Enrollment();
                enrollment.setAppointmentId(appointment.getId());
                enrollment.setStudentId(studentId);
                enrollment.setActivityId(appointment.getActivityId());
                enrollment.setCheckinStatus(0);
                enrollmentService.save(enrollment);
            }

            return appointment;
        } catch (Exception e) {
            activityService.decrementParticipants(appointment.getActivityId());
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelAppointment(Long id, String reason) {
        Appointment appointment = getById(id);
        if (appointment == null) {
            throw new RuntimeException("预约不存在");
        }
        if (appointment.getOrderStatus() != 1) {
            throw new RuntimeException("当前状态不可取消");
        }

        appointment.setOrderStatus(3);
        appointment.setCancelTime(LocalDateTime.now());
        appointment.setCancelReason(reason);
        updateById(appointment);

        activityService.decrementParticipants(appointment.getActivityId());

        return true;
    }

    private String generateOrderNo() {
        return "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
