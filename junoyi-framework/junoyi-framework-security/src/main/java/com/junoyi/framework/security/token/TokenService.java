package com.junoyi.framework.security.token;


import com.junoyi.framework.security.module.LoginUser;

/**
 * Token 业务接口类
 *
 * @author Fan
 */
public interface TokenService {

    /**
     * 创建访问令牌
     * 根据登录用户信息生成访问令牌
     * @param loginUser 登录用户信息
     * @return 访问令牌字符串
     */
    public String createAccessToken(LoginUser loginUser);

    /**
     * 解析访问令牌
     * 将访问令牌解析为登录用户信息
     * @param accessToken 访问令牌字符串
     * @return 登录用户信息
     */
    public LoginUser paresAccessToken(String accessToken);

    /**
     * 验证访问令牌
     * 验证访问令牌的有效性
     * @param accessToken 访问令牌字符串
     * @return 验证结果，true表示有效，false表示无效
     */
    public boolean validateAccessToken(String accessToken);

    /**
     * 创建刷新令牌
     * 根据登录用户信息生成刷新令牌
     * @param loginUser 登录用户信息
     * @return 刷新令牌字符串
     */
    public String createRefreshToken(LoginUser loginUser);

    /**
     * 解析刷新令牌
     * 将刷新令牌解析为登录用户信息
     * @param refreshToken 刷新令牌字符串
     * @return 登录用户信息
     */
    public LoginUser pareRefreshToken(String refreshToken);

    /**
     * 验证刷新令牌
     * 验证刷新令牌的有效性
     * @param refreshToken 刷新令牌字符串
     * @return 验证结果，true表示有效，false表示无效
     */
    public boolean validateRefreshToken(String refreshToken);
}
