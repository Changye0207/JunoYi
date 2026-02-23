package com.junoyi.module.activity.api.service;

import com.junoyi.module.activity.api.domain.entity.Activity;
import com.junoyi.module.activity.api.domain.vo.ActivityVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IActivityService extends IService<Activity> {
    List<ActivityVO> selectActivityList(Activity activity);
    ActivityVO selectActivityById(Long id);
    List<ActivityVO> selectActivityListByOrganizerId(Long organizerId);
    int insertActivity(Activity activity);
    int updateActivity(Activity activity);
    int publishActivity(Long id);
    int offlineActivity(Long id);
    boolean checkActivityAvailable(Long id);
    boolean incrementParticipants(Long id);
    boolean decrementParticipants(Long id);
    boolean incrementViewCount(Long id);
}
