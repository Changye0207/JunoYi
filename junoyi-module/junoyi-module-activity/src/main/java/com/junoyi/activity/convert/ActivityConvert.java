package com.junoyi.module.activity.convert;

import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ActivityConvert {
    ActivityConvert INSTANCE = Mappers.getMapper(ActivityConvert.class);

    ActivityVO toVO(Activity activity);
    List<ActivityVO> toVOList(List<Activity> activityList);
}
