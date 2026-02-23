package com.junoyi.module.activity.convert;

import com.junoyi.activity.api.domain.entity.ActivityCategory;
import com.junoyi.activity.api.domain.vo.ActivityCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ActivityCategoryConvert {
    ActivityCategoryConvert INSTANCE = Mappers.getMapper(ActivityCategoryConvert.class);

    ActivityCategoryVO toVO(ActivityCategory activityCategory);
    List<ActivityCategoryVO> toVOList(List<ActivityCategory> activityCategoryList);
}
