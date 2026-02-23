package com.junoyi.module.growth.api.service;

import com.junoyi.module.growth.api.domain.entity.GrowthRecord;
import com.junoyi.module.growth.api.domain.vo.GrowthRecordVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IGrowthRecordService extends IService<GrowthRecord> {
    List<GrowthRecordVO> selectGrowthRecordList(GrowthRecord growthRecord);
    GrowthRecordVO selectGrowthRecordById(Long id);
    List<GrowthRecordVO> selectGrowthRecordListByStudentId(Long studentId);
    int insertGrowthRecord(GrowthRecord growthRecord);
    int updateGrowthRecord(GrowthRecord growthRecord);
    boolean incrementViewCount(Long id);
}
