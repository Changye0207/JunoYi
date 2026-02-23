package com.junoyi.module.appointment.convert;

import com.junoyi.module.appointment.api.domain.entity.Appointment;
import com.junoyi.module.appointment.api.domain.vo.AppointmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface AppointmentConvert {
    AppointmentConvert INSTANCE = Mappers.getMapper(AppointmentConvert.class);

    AppointmentVO toVO(Appointment appointment);
    List<AppointmentVO> toVOList(List<Appointment> appointmentList);
}
