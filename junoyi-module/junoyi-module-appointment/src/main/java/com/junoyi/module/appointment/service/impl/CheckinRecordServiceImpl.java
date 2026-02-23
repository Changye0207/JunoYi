package com.junoyi.module.appointment.service.impl;

import com.junoyi.module.appointment.api.domain.entity.CheckinRecord;
import com.junoyi.module.appointment.api.domain.vo.CheckinRecordVO;
import com.junoyi.module.appointment.api.mapper.CheckinRecordMapper;
import com.junoyi.module.appointment.api.service.ICheckinRecordService;
import com.junoyi.module.appointment.convert.CheckinRecordConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class CheckinRecordServiceImpl extends ServiceImpl<CheckinRecordMapper, CheckinRecord>
        implements ICheckinRecordService {

    @Override
    public List<CheckinRecordVO> selectCheckinRecordList(CheckinRecord checkinRecord) {
        LambdaQueryWrapper<CheckinRecord> wrapper = new LambdaQueryWrapper<>();
        if (checkinRecord.getActivityId() != null) {
            wrapper.eq(CheckinRecord::getActivityId, checkinRecord.getActivityId());
        }
        if (checkinRecord.getEnrollmentId() != null) {
            wrapper.eq(CheckinRecord::getEnrollmentId, checkinRecord.getEnrollmentId());
        }
        if (checkinRecord.getStudentId() != null) {
            wrapper.eq(CheckinRecord::getStudentId, checkinRecord.getStudentId());
        }
        List<CheckinRecord> list = list(wrapper);
        return CheckinRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    public CheckinRecordVO selectCheckinRecordById(Long id) {
        CheckinRecord checkinRecord = getById(id);
        return CheckinRecordConvert.INSTANCE.toVO(checkinRecord);
    }

    @Override
    public List<CheckinRecordVO> selectCheckinRecordListByActivityId(Long activityId) {
        LambdaQueryWrapper<CheckinRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CheckinRecord::getActivityId, activityId);
        List<CheckinRecord> list = list(wrapper);
        return CheckinRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    public List<CheckinRecordVO> selectCheckinRecordListByEnrollmentId(Long enrollmentId) {
        LambdaQueryWrapper<CheckinRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CheckinRecord::getEnrollmentId, enrollmentId);
        List<CheckinRecord> list = list(wrapper);
        return CheckinRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    public int insertCheckinRecord(CheckinRecord checkinRecord) {
        return baseMapper.insert(checkinRecord);
    }

    @Override
    public int updateCheckinRecord(CheckinRecord checkinRecord) {
        return baseMapper.updateById(checkinRecord);
    }

    @Override
    public int deleteCheckinRecordByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
