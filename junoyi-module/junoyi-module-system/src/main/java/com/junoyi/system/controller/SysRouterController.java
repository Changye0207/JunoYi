package com.junoyi.system.controller;

import com.junoyi.framework.security.module.LoginUser;
import com.junoyi.framework.web.domain.BaseController;
import com.junoyi.framework.core.domain.module.R;
import com.junoyi.framework.security.annotation.PlatformScope;
import com.junoyi.framework.security.enums.PlatformType;
import com.junoyi.system.service.ISysMenuService;
import com.junoyi.system.service.ISysPermissionService;
import com.junoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统路由控制器
 * 提供系统路由相关的API接口
 *
 * @author Fan
 */
@RestController
@RequestMapping("/router")
@RequiredArgsConstructor
public class SysRouterController extends BaseController {

    private final ISysUserService sysUserService;

    private final ISysMenuService sysMenuService;

    private final ISysPermissionService sysPermissionService;

    /**
     * 获取路由信息
     * 该接口用于获取系统的路由配置信息
     *
     * @return R<?> 通用响应结果，包含路由数据
     */
    @GetMapping
    @PlatformScope(PlatformType.ADMIN_WEB)
    public R<?> getRouter(){
        // 通过用户来获取不同路由

        LoginUser loginUser = getLoginUser();

        return R.ok();
    }
}
