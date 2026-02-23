package com.junoyi.module.user.service.impl;

import com.junoyi.module.user.api.domain.entity.PlatformAdmin;
import com.junoyi.module.user.api.domain.vo.PlatformAdminVO;
import com.junoyi.module.user.api.mapper.PlatformAdminMapper;
import com.junoyi.module.user.api.service.IPlatformAdminService;
import com.junoyi.module.user.convert.PlatformAdminConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;

@Service
public class PlatformAdminServiceImpl extends ServiceImpl<PlatformAdminMapper, PlatformAdmin>
        implements IPlatformAdminService {

    @Override
    public List<PlatformAdminVO> selectPlatformAdminList(PlatformAdmin platformAdmin) {
        LambdaQueryWrapper<PlatformAdmin> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(platformAdmin.getRealName())) {
            wrapper.like(PlatformAdmin::getRealName, platformAdmin.getRealName());
        }
        if (StringUtils.hasText(platformAdmin.getPhone())) {
            wrapper.like(PlatformAdmin::getPhone, platformAdmin.getPhone());
        }
        if (platformAdmin.getStatus() != null) {
            wrapper.eq(PlatformAdmin::getStatus, platformAdmin.getStatus());
        }
        List<PlatformAdmin> list = list(wrapper);
        return PlatformAdminConvert.INSTANCE.toVOList(list);
    }

    @Override
    public PlatformAdminVO selectPlatformAdminById(Long id) {
        PlatformAdmin platformAdmin = getById(id);
        return PlatformAdminConvert.INSTANCE.toVO(platformAdmin);
    }

    @Override
    public PlatformAdminVO selectPlatformAdminByUserId(Long userId) {
        LambdaQueryWrapper<PlatformAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PlatformAdmin::getUserId, userId);
        PlatformAdmin platformAdmin = getOne(wrapper);
        return PlatformAdminConvert.INSTANCE.toVO(platformAdmin);
    }

    @Override
    public int insertPlatformAdmin(PlatformAdmin platformAdmin) {
        return baseMapper.insert(platformAdmin);
    }

    @Override
    public int updatePlatformAdmin(PlatformAdmin platformAdmin) {
        return baseMapper.updateById(platformAdmin);
    }

    @Override
    public int deletePlatformAdminByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
