package com.junoyi.module.appointment.api.service;

import com.junoyi.module.appointment.api.domain.entity.Enrollment;
import com.junoyi.module.appointment.api.domain.vo.EnrollmentVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IEnrollmentService extends IService<Enrollment> {
    List<EnrollmentVO> selectEnrollmentList(Enrollment enrollment);
    EnrollmentVO selectEnrollmentById(Long id);
    List<EnrollmentVO> selectEnrollmentListByAppointmentId(Long appointmentId);
    List<EnrollmentVO> selectEnrollmentListByActivityId(Long activityId);
    int insertEnrollment(Enrollment enrollment);
    int updateEnrollment(Enrollment enrollment);
    int deleteEnrollmentByIds(Long[] ids);
}
