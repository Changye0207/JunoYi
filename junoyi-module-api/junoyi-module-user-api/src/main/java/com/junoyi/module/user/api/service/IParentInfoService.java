package com.junoyi.module.user.api.service;

import com.junoyi.module.user.api.domain.entity.ParentInfo;
import com.junoyi.module.user.api.domain.vo.ParentInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IParentInfoService extends IService<ParentInfo> {
    List<ParentInfoVO> selectParentInfoList(ParentInfo parentInfo);
    ParentInfoVO selectParentInfoById(Long id);
    ParentInfoVO selectParentInfoByUserId(Long userId);
    int insertParentInfo(ParentInfo parentInfo);
    int updateParentInfo(ParentInfo parentInfo);
    int deleteParentInfoByIds(Long[] ids);
}
