package com.junoyi.module.growth.convert;

import com.junoyi.module.growth.api.domain.entity.GrowthAttachment;
import com.junoyi.module.growth.api.domain.vo.GrowthAttachmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface GrowthAttachmentConvert {
    GrowthAttachmentConvert INSTANCE = Mappers.getMapper(GrowthAttachmentConvert.class);

    GrowthAttachmentVO toVO(GrowthAttachment growthAttachment);
    List<GrowthAttachmentVO> toVOList(List<GrowthAttachment> growthAttachmentList);
}
