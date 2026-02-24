package com.junoyi.module.activity.api.mapper;

import com.junoyi.module.activity.api.domain.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    int incrementParticipants(@Param("activityId") Long activityId);
    int decrementParticipants(@Param("activityId") Long activityId);
    int incrementViewCount(@Param("activityId") Long activityId);
}
