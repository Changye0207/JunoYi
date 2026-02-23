package com.junoyi.module.appointment.api.service;

import com.junoyi.module.appointment.api.domain.entity.Appointment;
import com.junoyi.module.appointment.api.domain.vo.AppointmentVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IAppointmentService extends IService<Appointment> {
    List<AppointmentVO> selectAppointmentList(Appointment appointment);
    AppointmentVO selectAppointmentById(Long id);
    List<AppointmentVO> selectAppointmentListByParentId(Long parentId);
    List<AppointmentVO> selectAppointmentListByOrganizerId(Long organizerId);
    Appointment createAppointment(Appointment appointment, List<Long> studentIds);
    boolean cancelAppointment(Long id, String reason);
}
