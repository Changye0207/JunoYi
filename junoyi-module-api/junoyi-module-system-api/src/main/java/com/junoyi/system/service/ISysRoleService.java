package com.junoyi.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.system.domain.dto.SysRoleQueryDTO;
import com.junoyi.system.domain.po.SysRole;
import com.junoyi.system.domain.vo.SysRoleVO;

import java.util.List;


/**
 * 系统角色业务接口类
 *
 * @author Fan
 */
public interface ISysRoleService {

    /**
     * 查询角色列表
     * @param queryDTO 查询参数
     * @return 角色
     */
    PageResult<SysRoleVO> getRoleList(SysRoleQueryDTO queryDTO, Page<SysRole> page);

    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<SysRoleVO> getRoleList();
}
