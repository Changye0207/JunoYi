package com.junoyi.module.activity.api.service;

import com.junoyi.module.activity.api.domain.entity.ActivityCategory;
import com.junoyi.module.activity.api.domain.vo.ActivityCategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IActivityCategoryService extends IService<ActivityCategory> {
    List<ActivityCategoryVO> selectActivityCategoryList(ActivityCategory activityCategory);
    ActivityCategoryVO selectActivityCategoryById(Long id);
    ActivityCategoryVO selectActivityCategoryByCode(String code);
    int insertActivityCategory(ActivityCategory activityCategory);
    int updateActivityCategory(ActivityCategory activityCategory);
    int deleteActivityCategoryByIds(Long[] ids);
}
