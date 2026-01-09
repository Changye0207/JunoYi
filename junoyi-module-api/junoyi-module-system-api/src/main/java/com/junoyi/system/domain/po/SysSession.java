package com.junoyi.system.domain.po;

import com.junoyi.framework.security.enums.PlatformType;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * 系统会话数据实体
 * 用于存储和管理系统用户的会话信息，包括用户基本信息、权限角色、登录信息等
 *
 * @author Fan
 */
@Data
public class SysSession {

    /**
     * 会话ID，唯一标识一个用户会话
     */
    private String sessionId;

    /**
     * 用户ID，标识会话所属的用户
     */
    private Long userId;

    /**
     * 用户名，用户的登录名称
     */
    private String userName;

    /**
     * 昵称，用户显示的名称
     */
    private String nickName;

    /**
     * 平台类型，标识用户登录的平台
     */
    private PlatformType platformType;

    /**
     * 用户角色集合，存储用户拥有的角色ID
     */
    private Set<Long> roles;

    /**
     * 权限集合，存储用户拥有的权限标识
     */
    private Set<String> permissions;

    /**
     * 用户组集合，存储用户所属的用户组
     */
    private Set<String> groups;

    /**
     * 部门集合，存储用户所属的部门ID
     */
    private Set<Long> depts;

    /**
     * 登录IP地址，记录用户登录时的IP
     */
    private String loginIp;

    /**
     * IP所在地区，记录登录IP的地理位置信息
     */
    private String ipRegion;

    /**
     * 登录时间，记录用户本次会话的登录时间
     */
    private Date loginTime;

    /**
     * 最后访问时间，记录用户最后一次访问的时间，用于会话超时管理
     */
    private Date lastAccessTime;

    /**
     * AccessToken 过期时间
     */
    private Long accessExpireTime;

    /**
     * RefreshToken 过期时间
     */
    private Long refreshExpireTime;

}
