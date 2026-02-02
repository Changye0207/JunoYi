package com.junoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.junoyi.system.domain.po.SysAuthLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统登录日志 Mapper 接口类
 *
 * @author Fam
 */
@Mapper
public interface SysAuthLogMapper extends BaseMapper<SysAuthLog> {
}
