package com.junoyi.module.appointment.convert;

import com.junoyi.module.appointment.api.domain.entity.Enrollment;
import com.junoyi.module.appointment.api.domain.vo.EnrollmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface EnrollmentConvert {
    EnrollmentConvert INSTANCE = Mappers.getMapper(EnrollmentConvert.class);

    EnrollmentVO toVO(Enrollment enrollment);
    List<EnrollmentVO> toVOList(List<Enrollment> enrollmentList);
}
