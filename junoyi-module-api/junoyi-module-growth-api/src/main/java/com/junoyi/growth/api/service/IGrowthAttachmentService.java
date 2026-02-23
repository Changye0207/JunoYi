package com.junoyi.module.growth.api.service;

import com.junoyi.module.growth.api.domain.entity.GrowthAttachment;
import com.junoyi.module.growth.api.domain.vo.GrowthAttachmentVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IGrowthAttachmentService extends IService<GrowthAttachment> {
    List<GrowthAttachmentVO> selectGrowthAttachmentList(GrowthAttachment growthAttachment);
    GrowthAttachmentVO selectGrowthAttachmentById(Long id);
    List<GrowthAttachmentVO> selectGrowthAttachmentListByRecordId(Long recordId);
    int insertGrowthAttachment(GrowthAttachment growthAttachment);
    int updateGrowthAttachment(GrowthAttachment growthAttachment);
    int deleteGrowthAttachmentByIds(Long[] ids);
}
