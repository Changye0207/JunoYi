package com.junoyi.framework.security;

import com.junoyi.framework.security.module.LoginUser;

/**
 * token 业务接口类
 *
 * @author Fan
 */
public interface TokenService {

    /**
     * 创建访问令牌
     *
     * @param loginUser 登录用户信息
     * @return 生成的访问令牌字符串
     */
    String createAccessToken(LoginUser loginUser);

    /**
     * 解析访问令牌
     *
     * @param accessToken 访问令牌字符串
     * @return 解析出的登录用户信息
     */
    LoginUser paresAccessToken(String accessToken);

    /**
     * 验证访问令牌有效性
     *
     * @param accessToken 待验证的访问令牌字符串
     * @return 验证结果，有效返回true，无效返回false
     */
    boolean validateAccessToken(String accessToken);

    /**
     * 创建刷新令牌
     *
     * @param loginUser 登录用户信息
     * @return 生成的刷新令牌字符串
     */
    String createRefreshToken(LoginUser loginUser);

    /**
     * 解析刷新令牌
     *
     * @param refreshToken 刷新令牌字符串
     * @return 解析出的登录用户信息
     */
    LoginUser pareRefreshToken(String refreshToken);

    /**
     * 验证刷新令牌有效性
     *
     * @param refreshToken 待验证的刷新令牌字符串
     * @return 验证结果，有效返回true，无效返回false
     */
    boolean validateRefreshToken(String refreshToken);

}
