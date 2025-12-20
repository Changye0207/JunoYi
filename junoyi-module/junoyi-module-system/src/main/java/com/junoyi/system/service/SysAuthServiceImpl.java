package com.junoyi.system.service;


import com.junoyi.framework.core.utils.StringUtils;
import com.junoyi.framework.security.service.AuthService;
import com.junoyi.framework.security.token.TokenService;
import com.junoyi.system.domain.dto.LoginRequest;
import com.junoyi.system.domain.po.LoginIdentity;
import com.junoyi.system.domain.vo.AuthVo;
import com.junoyi.system.enums.LoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements ISysAuthService{

    private final AuthService authService;

    private final TokenService tokenService;

    @Override
    public AuthVo login(LoginRequest loginRequest) {
        LoginIdentity loginIdentity = pareIdentity(loginRequest);

        // 通过账户查用户

        // 登录校验

        // 校验密码

        // 构建loginUser

        // 生成 Token

        //

        return null;
    }

    /**
     * 解析登录账号类型
     */
    private LoginIdentity pareIdentity(LoginRequest request){
        if (StringUtils.isNotBlank(request.getPhonenumber())) {
            return new LoginIdentity(LoginType.PHONENUMBER, request.getPhonenumber());
        }

        if (StringUtils.isNotBlank(request.getEmail())) {
            return new LoginIdentity(LoginType.EMAIL, request.getEmail());
        }

        if (StringUtils.isNotBlank(request.getUsername())) {
            return new LoginIdentity(LoginType.USERNAME, request.getUsername());
        }
        throw new RuntimeException("登录账号不能为空");
    }
}