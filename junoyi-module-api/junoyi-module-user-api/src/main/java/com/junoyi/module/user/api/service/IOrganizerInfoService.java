package com.junoyi.module.user.api.service;

import com.junoyi.module.user.api.domain.entity.OrganizerInfo;
import com.junoyi.module.user.api.domain.vo.OrganizerInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IOrganizerInfoService extends IService<OrganizerInfo> {
    List<OrganizerInfoVO> selectOrganizerInfoList(OrganizerInfo organizerInfo);
    OrganizerInfoVO selectOrganizerInfoById(Long id);
    OrganizerInfoVO selectOrganizerInfoByUserId(Long userId);
    int insertOrganizerInfo(OrganizerInfo organizerInfo);
    int updateOrganizerInfo(OrganizerInfo organizerInfo);
    int deleteOrganizerInfoByIds(Long[] ids);
}
