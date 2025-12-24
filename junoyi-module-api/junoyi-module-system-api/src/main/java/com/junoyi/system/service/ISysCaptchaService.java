package com.junoyi.system.service;

import com.junoyi.framework.captcha.domain.CaptchaResult;

/**
 * 验证码服务接口
 *
 * @author Fan
 */
public interface ISysCaptchaService {

    /**
     * 获取图形验证码
     *
     * @return 验证码结果
     */
    CaptchaResult getImageCaptcha();

    /**
     * 校验验证码
     *
     * @param captchaId 验证码ID
     * @param code      用户输入的验证码
     * @return 是否校验通过
     */
    boolean validate(String captchaId, String code);
}
