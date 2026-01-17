package com.junoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.junoyi.framework.datasource.datascope.annotation.IgnoreDataScope;
import com.junoyi.system.domain.po.SysRoleGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限组关联 Mapper
 *
 * @author Fan
 */
@Mapper
@IgnoreDataScope
public interface SysRoleGroupMapper extends BaseMapper<SysRoleGroup> {
}
