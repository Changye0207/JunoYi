package com.junoyi.system.service;

import com.junoyi.system.domain.po.SysUser;

/**
 * 系统用户业务接口类
 *
 * @author Fan
 */
public interface ISysUserService {

    SysUser getSysUserById(Long userId);
}
