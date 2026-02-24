package com.junoyi.activity.service.impl;

import com.junoyi.activity.api.domain.entity.ActivityCategory;
import com.junoyi.activity.api.domain.vo.ActivityCategoryVO;
import com.junoyi.activity.api.mapper.ActivityCategoryMapper;
import com.junoyi.activity.api.service.IActivityCategoryService;
import com.junoyi.activity.convert.ActivityCategoryConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;

/**
 * 活动分类服务实现类
 * 实现活动分类相关的业务操作方法，包括查询、创建、更新、删除等功能
 * 继承自 MyBatis-Plus 的 ServiceImpl，提供基础的 CRUD 操作
 */
@Service
public class ActivityCategoryServiceImpl extends ServiceImpl<ActivityCategoryMapper, ActivityCategory>
        implements IActivityCategoryService {

    /**
     * 查询活动分类列表
     * 根据条件查询活动分类列表，并返回包含分类详细信息的VO列表
     * @param activityCategory 查询条件
     * @return 活动分类VO列表
     */
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

    /**
     * 根据ID查询活动分类
     * 根据分类ID查询分类的详细信息
     * @param id 分类ID
     * @return 活动分类VO
     */
    @Override
    public ActivityCategoryVO selectActivityCategoryById(Long id) {
        ActivityCategory activityCategory = getById(id);
        return ActivityCategoryConvert.INSTANCE.toVO(activityCategory);
    }

    /**
     * 根据编码查询活动分类
     * 根据分类编码查询分类的详细信息
     * @param code 分类编码
     * @return 活动分类VO
     */
    @Override
    public ActivityCategoryVO selectActivityCategoryByCode(String code) {
        LambdaQueryWrapper<ActivityCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ActivityCategory::getCode, code);
        ActivityCategory activityCategory = getOne(wrapper);
        return ActivityCategoryConvert.INSTANCE.toVO(activityCategory);
    }

    /**
     * 新增活动分类
     * 创建一条新的活动分类记录
     * @param activityCategory 活动分类实体
     * @return 影响行数
     */
    @Override
    public int insertActivityCategory(ActivityCategory activityCategory) {
        return baseMapper.insert(activityCategory);
    }

    /**
     * 更新活动分类
     * 更新活动分类的基本信息
     * @param activityCategory 活动分类实体
     * @return 影响行数
     */
    @Override
    public int updateActivityCategory(ActivityCategory activityCategory) {
        return baseMapper.updateById(activityCategory);
    }

    /**
     * 批量删除活动分类
     * 根据分类ID数组删除多个活动分类
     * @param ids 分类ID数组
     * @return 影响行数
     */
    @Override
    public int deleteActivityCategoryByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
