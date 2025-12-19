package com.junoyi.framework.security.spi;


import com.junoyi.framework.security.module.LoginUser;

/**
 * 用户详情提供者接口
 * <p>
 * 该接口定义了用户信息加载的标准方法，用于根据不同的标识符获取用户详情信息。
 * 实现类需要提供根据用户ID和平台UID加载用户信息的具体实现。
 * </p>
 *
 * @author Fan
 */
public interface UserDetailsProvider {
    /**
     * 根据用户ID加载用户
     * @param userId 用户ID
     * @return 登录用户信息
     */
    LoginUser loadByUserId(Long userId);

    /**
     * 根据平台UID（平台唯一标识符）加载用户
     * @param platformUID 平台唯一标识符
     * @return 登录用户信息
     */
    LoginUser loadByPlatformUID(String platformUID);
}

