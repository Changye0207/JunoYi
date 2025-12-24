package com.junoyi.framework.core.exception.captcha;

/**
 * 验证码不支持类型异常类
 * 继承自CaptchaException，用于处理验证码类型不支持的异常情况
 * 错误码为501，错误类型为"UNSUPPORTED_TYPE"
 *
 * @author Fan
 */
public class CaptchaUnsupportedTypeException extends CaptchaException {

    /**
     * 构造函数
     * @param message 异常描述信息
     */
    public CaptchaUnsupportedTypeException(String message){
        super(501, message, "UNSUPPORTED_TYPE");
    }

    /**
     * 获取域名前缀
     * @return 返回父类的域名前缀
     */
    @Override
    public String getDomainPrefix() {
        return super.getDomainPrefix();
    }
}
