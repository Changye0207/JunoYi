package com.junoyi.system.service;

import com.junoyi.system.domain.po.SysUser;
import com.junoyi.system.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 系统用户业务接口实现类
 *
 * @author Fan
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    @Override
    public SysUser getSysUserById(Long userId) {
        return null;
    }
}