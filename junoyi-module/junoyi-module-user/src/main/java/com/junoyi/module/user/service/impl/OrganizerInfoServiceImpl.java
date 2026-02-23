package com.junoyi.module.user.service.impl;

import com.junoyi.module.user.api.domain.entity.OrganizerInfo;
import com.junoyi.module.user.api.domain.vo.OrganizerInfoVO;
import com.junoyi.module.user.api.mapper.OrganizerInfoMapper;
import com.junoyi.module.user.api.service.IOrganizerInfoService;
import com.junoyi.module.user.convert.OrganizerInfoConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;

@Service
public class OrganizerInfoServiceImpl extends ServiceImpl<OrganizerInfoMapper, OrganizerInfo>
        implements IOrganizerInfoService {

    @Override
    public List<OrganizerInfoVO> selectOrganizerInfoList(OrganizerInfo organizerInfo) {
        LambdaQueryWrapper<OrganizerInfo> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(organizerInfo.getName())) {
            wrapper.like(OrganizerInfo::getName, organizerInfo.getName());
        }
        if (StringUtils.hasText(organizerInfo.getContactPhone())) {
            wrapper.like(OrganizerInfo::getContactPhone, organizerInfo.getContactPhone());
        }
        if (organizerInfo.getStatus() != null) {
            wrapper.eq(OrganizerInfo::getStatus, organizerInfo.getStatus());
        }
        List<OrganizerInfo> list = list(wrapper);
        return OrganizerInfoConvert.INSTANCE.toVOList(list);
    }

    @Override
    public OrganizerInfoVO selectOrganizerInfoById(Long id) {
        OrganizerInfo organizerInfo = getById(id);
        return OrganizerInfoConvert.INSTANCE.toVO(organizerInfo);
    }

    @Override
    public OrganizerInfoVO selectOrganizerInfoByUserId(Long userId) {
        LambdaQueryWrapper<OrganizerInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrganizerInfo::getUserId, userId);
        OrganizerInfo organizerInfo = getOne(wrapper);
        return OrganizerInfoConvert.INSTANCE.toVO(organizerInfo);
    }

    @Override
    public int insertOrganizerInfo(OrganizerInfo organizerInfo) {
        return baseMapper.insert(organizerInfo);
    }

    @Override
    public int updateOrganizerInfo(OrganizerInfo organizerInfo) {
        return baseMapper.updateById(organizerInfo);
    }

    @Override
    public int deleteOrganizerInfoByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
