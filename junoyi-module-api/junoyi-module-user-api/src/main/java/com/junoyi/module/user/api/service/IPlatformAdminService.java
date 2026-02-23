package com.junoyi.module.user.api.service;

import com.junoyi.module.user.api.domain.entity.PlatformAdmin;
import com.junoyi.module.user.api.domain.vo.PlatformAdminVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IPlatformAdminService extends IService<PlatformAdmin> {
    List<PlatformAdminVO> selectPlatformAdminList(PlatformAdmin platformAdmin);
    PlatformAdminVO selectPlatformAdminById(Long id);
    PlatformAdminVO selectPlatformAdminByUserId(Long userId);
    int insertPlatformAdmin(PlatformAdmin platformAdmin);
    int updatePlatformAdmin(PlatformAdmin platformAdmin);
    int deletePlatformAdminByIds(Long[] ids);
}
