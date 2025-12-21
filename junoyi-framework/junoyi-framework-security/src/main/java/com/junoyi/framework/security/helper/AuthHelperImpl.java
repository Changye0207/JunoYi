package com.junoyi.framework.security.helper;

import com.junoyi.framework.security.enums.PlatformType;
import com.junoyi.framework.security.module.LoginUser;
import com.junoyi.framework.security.module.UserSession;
import com.junoyi.framework.security.module.TokenPair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认证服务助手实现
 *
 * @author Fan
 */
@Service
@RequiredArgsConstructor
public class AuthHelperImpl implements AuthHelper {

    private final SessionHelper sessionHelper;

    /**
     * 用户登录认证（推荐使用）
     *
     * @param loginUser    登录用户信息
     * @param platformType 登录平台类型
     * @param loginIp      登录IP地址
     * @param userAgent    用户代理信息
     * @return TokenPair 访问令牌和刷新令牌对
     */
    @Override
    public TokenPair login(LoginUser loginUser, PlatformType platformType, String loginIp, String userAgent) {
        // 设置平台类型
        loginUser.setPlatformType(platformType);
        return sessionHelper.login(loginUser, loginIp, userAgent);
    }

    /**
     * 用户登录认证（简化版，只传平台类型）
     *
     * @param loginUser    登录用户信息
     * @param platformType 登录平台类型
     * @return TokenPair 访问令牌和刷新令牌对
     */
    @Override
    public TokenPair login(LoginUser loginUser, PlatformType platformType) {
        return login(loginUser, platformType, null, null);
    }

    /**
     * 用户登录认证（使用默认平台 ADMIN_WEB）
     *
     * @param loginUser 登录用户信息
     * @param loginIp   登录IP地址
     * @param userAgent 用户代理信息
     * @return TokenPair 访问令牌和刷新令牌对
     */
    @Override
    public TokenPair login(LoginUser loginUser, String loginIp, String userAgent) {
        // 如果 loginUser 中已设置 platformType 则使用，否则默认 ADMIN_WEB
        PlatformType platform = loginUser.getPlatformType() != null 
                ? loginUser.getPlatformType() 
                : PlatformType.ADMIN_WEB;
        return login(loginUser, platform, loginIp, userAgent);
    }

    /**
     * 用户登录认证（最简版）
     *
     * @param loginUser 登录用户信息
     * @return TokenPair 访问令牌和刷新令牌对
     */
    @Override
    public TokenPair login(LoginUser loginUser) {
        return login(loginUser, PlatformType.ADMIN_WEB, null, null);
    }

    /**
     * 用户登出
     *
     * @param token 访问令牌
     * @return boolean 登出是否成功
     */
    @Override
    public boolean logout(String token) {
        return sessionHelper.logout(token);
    }

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @return TokenPair 新的访问令牌和刷新令牌对
     */
    @Override
    public TokenPair refresh(String refreshToken) {
        return sessionHelper.refreshToken(refreshToken);
    }

    /**
     * 根据令牌获取登录用户信息
     *
     * @param token 访问令牌
     * @return LoginUser 登录用户信息
     */
    @Override
    public LoginUser getLoginUser(String token) {
        return sessionHelper.getLoginUser(token);
    }

    /**
     * 根据令牌获取用户会话信息
     *
     * @param token 访问令牌
     * @return UserSession 用户会话信息
     */
    @Override
    public UserSession getSession(String token) {
        return sessionHelper.getSession(token);
    }

    /**
     * 根据令牌ID获取用户会话信息
     *
     * @param tokenId 令牌ID
     * @return UserSession 用户会话信息
     */
    @Override
    public UserSession getSessionByTokenId(String tokenId) {
        return sessionHelper.getSessionByTokenId(tokenId);
    }

    /**
     * 验证令牌是否有效
     *
     * @param token 访问令牌
     * @return boolean 令牌是否有效
     */
    @Override
    public boolean isValid(String token) {
        return sessionHelper.isValid(token);
    }

    /**
     * 更新用户权限信息
     *
     * @param tokenId 令牌ID
     * @param loginUser 更新后的登录用户信息
     * @return boolean 更新是否成功
     */
    @Override
    public boolean updatePermissions(String tokenId, LoginUser loginUser) {
        return sessionHelper.updateSession(tokenId, loginUser);
    }

    /**
     * 获取指定用户的全部会话信息
     *
     * @param userId 用户ID
     * @return List<UserSession> 用户会话列表
     */
    @Override
    public List<UserSession> getUserSessions(Long userId) {
        return sessionHelper.getUserSessions(userId);
    }

    /**
     * 强制踢出指定会话
     *
     * @param tokenId 令牌ID
     * @return boolean 踢出操作是否成功
     */
    @Override
    public boolean kickOut(String tokenId) {
        return sessionHelper.kickOut(tokenId);
    }

    /**
     * 强制踢出指定用户的所有会话
     *
     * @param userId 用户ID
     * @return int 被踢出会话的数量
     */
    @Override
    public int kickOutAll(Long userId) {
        return sessionHelper.kickOutAll(userId);
    }
}

