package com.junoyi.module.activity.service.impl;

import com.junoyi.activity.api.domain.entity.ActivityCategory;
import com.junoyi.activity.api.domain.vo.ActivityCategoryVO;
import com.junoyi.activity.api.mapper.ActivityCategoryMapper;
import com.junoyi.activity.api.service.IActivityCategoryService;
import com.junoyi.module.activity.convert.ActivityCategoryConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityCategoryServiceImpl extends ServiceImpl<ActivityCategoryMapper, ActivityCategory>
        implements IActivityCategoryService {

    @Override
    public List<ActivityCategoryVO> selectActivityCategoryList(ActivityCategory activityCategory) {
        LambdaQueryWrapper<ActivityCategory> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(activityCategory.getName())) {
            wrapper.like(ActivityCategory::getName, activityCategory.getName());
        }
        if (StringUtils.hasText(activityCategory.getCode())) {
            wrapper.like(ActivityCategory::getCode, activityCategory.getCode());
        }
        if (activityCategory.getStatus() != null) {
            wrapper.eq(ActivityCategory::getStatus, activityCategory.getStatus());
        }
        wrapper.orderByAsc(ActivityCategory::getSort);
        List<ActivityCategory> list = list(wrapper);
        return ActivityCategoryConvert.INSTANCE.toVOList(list);
    }

    @Override
    public ActivityCategoryVO selectActivityCategoryById(Long id) {
        ActivityCategory activityCategory = getById(id);
        return ActivityCategoryConvert.INSTANCE.toVO(activityCategory);
    }

    @Override
    public ActivityCategoryVO selectActivityCategoryByCode(String code) {
        LambdaQueryWrapper<ActivityCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ActivityCategory::getCode, code);
        ActivityCategory activityCategory = getOne(wrapper);
        return ActivityCategoryConvert.INSTANCE.toVO(activityCategory);
    }

    @Override
    public int insertActivityCategory(ActivityCategory activityCategory) {
        return baseMapper.insert(activityCategory);
    }

    @Override
    public int updateActivityCategory(ActivityCategory activityCategory) {
        return baseMapper.updateById(activityCategory);
    }

    @Override
    public int deleteActivityCategoryByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
