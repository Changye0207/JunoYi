package com.junoyi.framework.security.context;

import com.junoyi.framework.security.module.LoginUser;

/**
 * 安全上下文工具类
 * 用于在当前线程中存储和获取登录用户信息，基于ThreadLocal实现线程隔离
 *
 * @author Fan
 */
public class SecurityContext {

    private static final ThreadLocal<LoginUser> CONTEXT = new ThreadLocal<>();

    /**
     * 设置当前线程的登录用户信息
     *
     * @param loginUser 登录用户对象，可以为null
     */
    public static void set(LoginUser loginUser){
        CONTEXT.set(loginUser);
    }

    /**
     * 获取当前线程的登录用户信息
     *
     * @return 返回当前线程存储的登录用户对象，如果未设置则返回null
     */
    public static LoginUser get() {
        return CONTEXT.get();
    }

    /**
     * 获取当前线程登录用户的ID
     *
     * @return 返回当前登录用户的ID，如果未登录或用户信息不存在则返回null
     */
    public static Long getUserId() {
        LoginUser user = get();
        return user == null ? null : user.getUserId();
    }

    /**
     * 清除当前线程的登录用户信息
     * 用于释放ThreadLocal资源，防止内存泄漏
     */
    public static void clear() {
        CONTEXT.remove();
    }
}
