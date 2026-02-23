package com.junoyi.module.user.convert;

import com.junoyi.module.user.api.domain.entity.StudentInfo;
import com.junoyi.module.user.api.domain.vo.StudentInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface StudentInfoConvert {
    StudentInfoConvert INSTANCE = Mappers.getMapper(StudentInfoConvert.class);

    StudentInfoVO toVO(StudentInfo studentInfo);
    List<StudentInfoVO> toVOList(List<StudentInfo> studentInfoList);
}
