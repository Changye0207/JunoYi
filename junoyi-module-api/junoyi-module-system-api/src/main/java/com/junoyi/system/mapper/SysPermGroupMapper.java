package com.junoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.junoyi.framework.datasource.datascope.annotation.IgnoreDataScope;
import com.junoyi.system.domain.po.SysPermGroup;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限组 Mapper
 *
 * @author Fan
 */
@Mapper
@IgnoreDataScope
public interface SysPermGroupMapper extends BaseMapper<SysPermGroup> {
}
