package com.junoyi.module.user.service.impl;

import com.junoyi.module.user.api.domain.entity.ParentInfo;
import com.junoyi.module.user.api.domain.vo.ParentInfoVO;
import com.junoyi.module.user.api.mapper.ParentInfoMapper;
import com.junoyi.module.user.api.service.IParentInfoService;
import com.junoyi.module.user.convert.ParentInfoConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.List;

@Service
public class ParentInfoServiceImpl extends ServiceImpl<ParentInfoMapper, ParentInfo>
        implements IParentInfoService {

    @Override
    public List<ParentInfoVO> selectParentInfoList(ParentInfo parentInfo) {
        LambdaQueryWrapper<ParentInfo> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(parentInfo.getRealName())) {
            wrapper.like(ParentInfo::getRealName, parentInfo.getRealName());
        }
        if (StringUtils.hasText(parentInfo.getPhone())) {
            wrapper.like(ParentInfo::getPhone, parentInfo.getPhone());
        }
        List<ParentInfo> list = list(wrapper);
        return ParentInfoConvert.INSTANCE.toVOList(list);
    }

    @Override
    public ParentInfoVO selectParentInfoById(Long id) {
        ParentInfo parentInfo = getById(id);
        return ParentInfoConvert.INSTANCE.toVO(parentInfo);
    }

    @Override
    public ParentInfoVO selectParentInfoByUserId(Long userId) {
        LambdaQueryWrapper<ParentInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ParentInfo::getUserId, userId);
        ParentInfo parentInfo = getOne(wrapper);
        return ParentInfoConvert.INSTANCE.toVO(parentInfo);
    }

    @Override
    public int insertParentInfo(ParentInfo parentInfo) {
        return baseMapper.insert(parentInfo);
    }

    @Override
    public int updateParentInfo(ParentInfo parentInfo) {
        return baseMapper.updateById(parentInfo);
    }

    @Override
    public int deleteParentInfoByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
