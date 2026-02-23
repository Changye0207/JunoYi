package com.junoyi.module.growth.convert;

import com.junoyi.module.growth.api.domain.entity.GrowthRecord;
import com.junoyi.module.growth.api.domain.vo.GrowthRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface GrowthRecordConvert {
    GrowthRecordConvert INSTANCE = Mappers.getMapper(GrowthRecordConvert.class);

    GrowthRecordVO toVO(GrowthRecord growthRecord);
    List<GrowthRecordVO> toVOList(List<GrowthRecord> growthRecordList);
}
