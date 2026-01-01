package com.junoyi.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.system.convert.SysRoleConverter;
import com.junoyi.system.domain.dto.SysRoleQueryDTO;
import com.junoyi.system.domain.po.SysRole;
import com.junoyi.system.domain.vo.SysRoleVO;
import com.junoyi.system.mapper.SysRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 系统角色业务接口实现类
 *
 * @author Fan
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements ISysRoleService{

    private final SysRoleMapper sysRoleMapper;
    private final SysRoleConverter sysRoleConverter;

    /**
     * 查询角色列表
     * @param queryDTO 查询参数
     * @return 角色
     */
    @Override
    public PageResult<SysRoleVO> getRoleList(SysRoleQueryDTO queryDTO, Page<SysRole> page) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(queryDTO.getRoleName()), SysRole::getRoleName, queryDTO.getRoleName())
               .like(StringUtils.hasText(queryDTO.getRoleKey()), SysRole::getRoleKey, queryDTO.getRoleKey())
               .eq(queryDTO.getStatus() != null, SysRole::getStatus, queryDTO.getStatus())
               .eq(SysRole::isDelFlag, false)
               .orderByAsc(SysRole::getSort);

        Page<SysRole> resultPage = sysRoleMapper.selectPage(page, wrapper);
        return PageResult.of(sysRoleConverter.toVoList(resultPage.getRecords()), 
                resultPage.getTotal(), 
                (int) resultPage.getCurrent(), 
                (int) resultPage.getSize());
    }

    /**
     * 获取角色列表
     * @return 角色列表
     */
    @Override
    public List<SysRoleVO> getRoleList() {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::isDelFlag, false)
                .eq(SysRole::getStatus, 1);
        List<SysRole> sysRoles = sysRoleMapper.selectList(wrapper);
        return sysRoleConverter.toVoList(sysRoles);
    }
}