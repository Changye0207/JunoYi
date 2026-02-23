package com.junoyi.module.growth.service.impl;

import com.junoyi.module.growth.api.domain.entity.GrowthRecord;
import com.junoyi.module.growth.api.domain.vo.GrowthRecordVO;
import com.junoyi.module.growth.api.mapper.GrowthRecordMapper;
import com.junoyi.module.growth.api.service.IGrowthRecordService;
import com.junoyi.module.growth.convert.GrowthRecordConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
public class GrowthRecordServiceImpl extends ServiceImpl<GrowthRecordMapper, GrowthRecord>
        implements IGrowthRecordService {

    @Override
    public List<GrowthRecordVO> selectGrowthRecordList(GrowthRecord growthRecord) {
        LambdaQueryWrapper<GrowthRecord> wrapper = new LambdaQueryWrapper<>();
        if (growthRecord.getParentId() != null) {
            wrapper.eq(GrowthRecord::getParentId, growthRecord.getParentId());
        }
        if (growthRecord.getStudentId() != null) {
            wrapper.eq(GrowthRecord::getStudentId, growthRecord.getStudentId());
        }
        if (StringUtils.hasText(growthRecord.getTitle())) {
            wrapper.like(GrowthRecord::getTitle, growthRecord.getTitle());
        }
        if (growthRecord.getStatus() != null) {
            wrapper.eq(GrowthRecord::getStatus, growthRecord.getStatus());
        }
        wrapper.orderByDesc(GrowthRecord::getCreateTime);
        List<GrowthRecord> list = list(wrapper);
        return GrowthRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    public GrowthRecordVO selectGrowthRecordById(Long id) {
        incrementViewCount(id);
        GrowthRecord growthRecord = getById(id);
        return GrowthRecordConvert.INSTANCE.toVO(growthRecord);
    }

    @Override
    public List<GrowthRecordVO> selectGrowthRecordListByStudentId(Long studentId) {
        LambdaQueryWrapper<GrowthRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrowthRecord::getStudentId, studentId);
        wrapper.orderByDesc(GrowthRecord::getCreateTime);
        List<GrowthRecord> list = list(wrapper);
        return GrowthRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    public int insertGrowthRecord(GrowthRecord growthRecord) {
        return baseMapper.insert(growthRecord);
    }

    @Override
    public int updateGrowthRecord(GrowthRecord growthRecord) {
        return baseMapper.updateById(growthRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean incrementViewCount(Long id) {
        GrowthRecord growthRecord = new GrowthRecord();
        growthRecord.setId(id);
        // 这里可以使用乐观锁或其他方式实现原子操作
        return update().setSql("view_count = view_count + 1").eq("id", id).update();
    }
}
