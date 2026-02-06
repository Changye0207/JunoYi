package com.junoyi.system.controller;

import com.junoyi.framework.log.core.JunoYiLog;
import com.junoyi.framework.log.core.JunoYiLogFactory;
import com.junoyi.framework.web.domain.BaseController;
import com.junoyi.system.service.ISysUserCenterService;
import com.junoyi.system.service.impl.SysUserCenterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户个人中心控制器
 *
 * @author Fan
 */
@RestController
@RequestMapping("/system/user-center")
@RequiredArgsConstructor
public class SysUserCenterController extends BaseController {

    private final JunoYiLog log = JunoYiLogFactory.getLogger(SysUserCenterServiceImpl.class);

    private final ISysUserCenterService sysUserCenterService;


}