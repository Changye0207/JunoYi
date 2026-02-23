package com.junoyi.module.activity.service.impl;

import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import com.junoyi.activity.api.mapper.ActivityMapper;
import com.junoyi.activity.api.service.IActivityService;
import com.junoyi.module.activity.convert.ActivityConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
        implements IActivityService {

    @Override
    public List<ActivityVO> selectActivityList(Activity activity) {
        LambdaQueryWrapper<Activity> wrapper = new LambdaQueryWrapper<>();
        if (activity.getCategoryId() != null) {
            wrapper.eq(Activity::getCategoryId, activity.getCategoryId());
        }
        if (StringUtils.hasText(activity.getTitle())) {
            wrapper.like(Activity::getTitle, activity.getTitle());
        }
        if (activity.getStatus() != null) {
            wrapper.eq(Activity::getStatus, activity.getStatus());
        }
        wrapper.orderByDesc(Activity::getCreateTime);
        List<Activity> list = list(wrapper);
        return ActivityConvert.INSTANCE.toVOList(list);
    }

    @Override
    public ActivityVO selectActivityById(Long id) {
        incrementViewCount(id);
        Activity activity = getById(id);
        return ActivityConvert.INSTANCE.toVO(activity);
    }

    @Override
    public List<ActivityVO> selectActivityListByOrganizerId(Long organizerId) {
        LambdaQueryWrapper<Activity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Activity::getOrganizerId, organizerId);
        wrapper.orderByDesc(Activity::getCreateTime);
        List<Activity> list = list(wrapper);
        return ActivityConvert.INSTANCE.toVOList(list);
    }

    @Override
    public int insertActivity(Activity activity) {
        activity.setStatus(0);
        activity.setCurrentParticipants(0);
        activity.setViewCount(0);
        return baseMapper.insert(activity);
    }

    @Override
    public int updateActivity(Activity activity) {
        return baseMapper.updateById(activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int publishActivity(Long id) {
        Activity activity = new Activity();
        activity.setId(id);
        activity.setStatus(1);
        return baseMapper.updateById(activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int offlineActivity(Long id) {
        Activity activity = new Activity();
        activity.setId(id);
        activity.setStatus(2);
        return baseMapper.updateById(activity);
    }

    @Override
    public boolean checkActivityAvailable(Long id) {
        Activity activity = getById(id);
        if (activity == null || activity.getStatus() != 1) {
            return false;
        }
        if (activity.getSignUpDeadline() != null
                && LocalDateTime.now().isAfter(activity.getSignUpDeadline())) {
            return false;
        }
        return activity.getCurrentParticipants() < activity.getMaxParticipants();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean incrementParticipants(Long id) {
        return baseMapper.incrementParticipants(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean decrementParticipants(Long id) {
        return baseMapper.decrementParticipants(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean incrementViewCount(Long id) {
        return baseMapper.incrementViewCount(id) > 0;
    }
}
