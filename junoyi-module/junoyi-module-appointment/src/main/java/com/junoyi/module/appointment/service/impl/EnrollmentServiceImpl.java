package com.junoyi.module.appointment.service.impl;

import com.junoyi.module.appointment.api.domain.entity.Enrollment;
import com.junoyi.module.appointment.api.domain.vo.EnrollmentVO;
import com.junoyi.module.appointment.api.mapper.EnrollmentMapper;
import com.junoyi.module.appointment.api.service.IEnrollmentService;
import com.junoyi.module.appointment.convert.EnrollmentConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class EnrollmentServiceImpl extends ServiceImpl<EnrollmentMapper, Enrollment>
        implements IEnrollmentService {

    @Override
    public List<EnrollmentVO> selectEnrollmentList(Enrollment enrollment) {
        LambdaQueryWrapper<Enrollment> wrapper = new LambdaQueryWrapper<>();
        if (enrollment.getAppointmentId() != null) {
            wrapper.eq(Enrollment::getAppointmentId, enrollment.getAppointmentId());
        }
        if (enrollment.getStudentId() != null) {
            wrapper.eq(Enrollment::getStudentId, enrollment.getStudentId());
        }
        if (enrollment.getActivityId() != null) {
            wrapper.eq(Enrollment::getActivityId, enrollment.getActivityId());
        }
        List<Enrollment> list = list(wrapper);
        return EnrollmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    public EnrollmentVO selectEnrollmentById(Long id) {
        Enrollment enrollment = getById(id);
        return EnrollmentConvert.INSTANCE.toVO(enrollment);
    }

    @Override
    public List<EnrollmentVO> selectEnrollmentListByAppointmentId(Long appointmentId) {
        LambdaQueryWrapper<Enrollment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Enrollment::getAppointmentId, appointmentId);
        List<Enrollment> list = list(wrapper);
        return EnrollmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    public List<EnrollmentVO> selectEnrollmentListByActivityId(Long activityId) {
        LambdaQueryWrapper<Enrollment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Enrollment::getActivityId, activityId);
        List<Enrollment> list = list(wrapper);
        return EnrollmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    public int insertEnrollment(Enrollment enrollment) {
        return baseMapper.insert(enrollment);
    }

    @Override
    public int updateEnrollment(Enrollment enrollment) {
        return baseMapper.updateById(enrollment);
    }

    @Override
    public int deleteEnrollmentByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
