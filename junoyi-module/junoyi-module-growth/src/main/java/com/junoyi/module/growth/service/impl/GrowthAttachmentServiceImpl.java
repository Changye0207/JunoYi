package com.junoyi.module.growth.service.impl;

import com.junoyi.module.growth.api.domain.entity.GrowthAttachment;
import com.junoyi.module.growth.api.domain.vo.GrowthAttachmentVO;
import com.junoyi.module.growth.api.mapper.GrowthAttachmentMapper;
import com.junoyi.module.growth.api.service.IGrowthAttachmentService;
import com.junoyi.module.growth.convert.GrowthAttachmentConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class GrowthAttachmentServiceImpl extends ServiceImpl<GrowthAttachmentMapper, GrowthAttachment>
        implements IGrowthAttachmentService {

    @Override
    public List<GrowthAttachmentVO> selectGrowthAttachmentList(GrowthAttachment growthAttachment) {
        LambdaQueryWrapper<GrowthAttachment> wrapper = new LambdaQueryWrapper<>();
        if (growthAttachment.getRecordId() != null) {
            wrapper.eq(GrowthAttachment::getRecordId, growthAttachment.getRecordId());
        }
        wrapper.orderByAsc(GrowthAttachment::getSort);
        List<GrowthAttachment> list = list(wrapper);
        return GrowthAttachmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    public GrowthAttachmentVO selectGrowthAttachmentById(Long id) {
        GrowthAttachment growthAttachment = getById(id);
        return GrowthAttachmentConvert.INSTANCE.toVO(growthAttachment);
    }

    @Override
    public List<GrowthAttachmentVO> selectGrowthAttachmentListByRecordId(Long recordId) {
        LambdaQueryWrapper<GrowthAttachment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GrowthAttachment::getRecordId, recordId);
        wrapper.orderByAsc(GrowthAttachment::getSort);
        List<GrowthAttachment> list = list(wrapper);
        return GrowthAttachmentConvert.INSTANCE.toVOList(list);
    }

    @Override
    public int insertGrowthAttachment(GrowthAttachment growthAttachment) {
        return baseMapper.insert(growthAttachment);
    }

    @Override
    public int updateGrowthAttachment(GrowthAttachment growthAttachment) {
        return baseMapper.updateById(growthAttachment);
    }

    @Override
    public int deleteGrowthAttachmentByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
