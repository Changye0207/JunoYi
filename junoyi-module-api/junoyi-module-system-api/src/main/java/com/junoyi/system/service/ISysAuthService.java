package com.junoyi.system.service;

import com.junoyi.system.domain.dto.LoginRequest;
import com.junoyi.system.domain.vo.AuthVo;

/**
 * 系统验证认证业务接口类
 *
 * @author Fan
 */
public interface ISysAuthService {


    AuthVo login(LoginRequest loginRequest);
}
