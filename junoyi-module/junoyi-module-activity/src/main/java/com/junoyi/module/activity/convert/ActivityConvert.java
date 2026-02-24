package com.junoyi.activity.convert;

import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * 活动对象转换接口
 * 用于 Activity 实体类和 ActivityVO 值对象之间的转换
 * 使用 MapStruct 框架实现自动化的对象转换
 */
@Mapper
public interface ActivityConvert {
    ActivityConvert INSTANCE = Mappers.getMapper(ActivityConvert.class);

    /**
     * 将 Activity 实体转换为 ActivityVO
     * @param activity 活动实体对象
     * @return 活动值对象
     */
    ActivityVO toVO(Activity activity);

    /**
     * 将 Activity 实体列表转换为 ActivityVO 列表
     * @param activityList 活动实体列表
     * @return 活动值对象列表
     */
    List<ActivityVO> toVOList(List<Activity> activityList);
}
