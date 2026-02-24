package com.junoyi.activity.convert;

import com.junoyi.activity.api.domain.entity.ActivityCategory;
import com.junoyi.activity.api.domain.vo.ActivityCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * 活动分类对象转换接口
 * 用于 ActivityCategory 实体类和 ActivityCategoryVO 值对象之间的转换
 * 使用 MapStruct 框架实现自动化的对象转换
 */
@Mapper
public interface ActivityCategoryConvert {
    ActivityCategoryConvert INSTANCE = Mappers.getMapper(ActivityCategoryConvert.class);

    /**
     * 将 ActivityCategory 实体转换为 ActivityCategoryVO
     * @param activityCategory 活动分类实体对象
     * @return 活动分类值对象
     */
    ActivityCategoryVO toVO(ActivityCategory activityCategory);

    /**
     * 将 ActivityCategory 实体列表转换为 ActivityCategoryVO 列表
     * @param activityCategoryList 活动分类实体列表
     * @return 活动分类值对象列表
     */
    List<ActivityCategoryVO> toVOList(List<ActivityCategory> activityCategoryList);
}
