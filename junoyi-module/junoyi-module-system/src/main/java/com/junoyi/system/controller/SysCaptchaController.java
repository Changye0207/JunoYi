package com.junoyi.system.controller;

import com.junoyi.framework.captcha.domain.CaptchaResult;
import com.junoyi.framework.core.domain.module.R;
import com.junoyi.system.service.ISysCaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 验证码控制器
 *
 * @author Fan
 */
@RestController
@RequestMapping("/captcha")
@RequiredArgsConstructor

public class SysCaptchaController {

    private final ISysCaptchaService captchaService;

    /**
     * 获取图形验证码
     */
    @GetMapping("/image")
    public R<CaptchaResult> getImageCaptcha() {
        return R.ok(captchaService.getImageCaptcha());
    }

    /**
     * 获取滑块验证码
     */
    @GetMapping("/slider")
    public R<CaptchaResult> getSliderCaptcha() {
        return R.ok(captchaService.getSliderCaptcha());
    }

    /**
     * 校验图片验证码
     */
    @PostMapping("/validate")
    public R<Boolean> validate(@RequestParam String captchaId, @RequestParam String code) {
        return R.ok(captchaService.validate(captchaId, code));
    }

    /**
     * 校验滑块验证码
     * @param captchaId 验证码ID
     * @param pointJson 滑块坐标JSON，格式: {"x":100,"y":5}
     */
    @PostMapping("/validate/slider")
    public R<Boolean> validateSlider(@RequestParam("captchaId") String captchaId, @RequestParam("pointJson") String pointJson) {
        return R.ok(captchaService.validateSlider(captchaId, pointJson));
    }
}
