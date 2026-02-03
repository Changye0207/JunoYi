package com.junoyi.system.service.impl;

import com.junoyi.system.mapper.SysOperLogMapper;
import com.junoyi.system.service.ISysOperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 系统操作日志业务接口实现类
 *
 * @author Fan
 */
@Service
@RequiredArgsConstructor
public class SysOperLogServiceImpl implements ISysOperLogService {

    private final SysOperLogMapper sysOperLogMapper;


}