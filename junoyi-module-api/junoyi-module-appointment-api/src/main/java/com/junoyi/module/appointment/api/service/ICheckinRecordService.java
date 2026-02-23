package com.junoyi.module.appointment.api.service;

import com.junoyi.module.appointment.api.domain.entity.CheckinRecord;
import com.junoyi.module.appointment.api.domain.vo.CheckinRecordVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface ICheckinRecordService extends IService<CheckinRecord> {
    List<CheckinRecordVO> selectCheckinRecordList(CheckinRecord checkinRecord);
    CheckinRecordVO selectCheckinRecordById(Long id);
    List<CheckinRecordVO> selectCheckinRecordListByActivityId(Long activityId);
    List<CheckinRecordVO> selectCheckinRecordListByEnrollmentId(Long enrollmentId);
    int insertCheckinRecord(CheckinRecord checkinRecord);
    int updateCheckinRecord(CheckinRecord checkinRecord);
    int deleteCheckinRecordByIds(Long[] ids);
}
