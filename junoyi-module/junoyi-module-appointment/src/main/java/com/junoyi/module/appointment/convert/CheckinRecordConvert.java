package com.junoyi.module.appointment.convert;

import com.junoyi.module.appointment.api.domain.entity.CheckinRecord;
import com.junoyi.module.appointment.api.domain.vo.CheckinRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface CheckinRecordConvert {
    CheckinRecordConvert INSTANCE = Mappers.getMapper(CheckinRecordConvert.class);

    CheckinRecordVO toVO(CheckinRecord checkinRecord);
    List<CheckinRecordVO> toVOList(List<CheckinRecord> checkinRecordList);
}
