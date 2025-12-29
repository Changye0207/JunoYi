package com.junoyi.system.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * 角色与权限组关联数据实体
 * 用于存储系统中角色与权限组之间的关联关系，包含关联ID、角色ID、权限组ID、过期时间等信息
 *
 * @author Fan
 */
@Data
public class SysRoleGroup {

    /**
     * 关联记录的唯一标识ID
     */
    private Long id;

    /**
     * 角色ID，关联系统角色表
     */
    private Long roleId;

    /**
     * 权限组ID，关联权限组表
     */
    private Long groupId;

    /**
     * 关联关系过期时间，超过此时间后关联关系失效
     */
    private Date expireTime;

    /**
     * 记录创建时间
     */
    private Date createTime;
}
