package com.junoyi.framework.web.exception;

import com.junoyi.framework.core.domain.base.BaseException;
import com.junoyi.framework.core.domain.module.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * 该类用于统一处理应用程序中抛出的异常，提供全局的异常捕获和处理机制。
 * 通过使用@RestControllerAdvice注解，可以捕获所有控制器层抛出的异常，
 * 并返回统一格式的错误响应给客户端。
 *
 * @author Fan
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public R<?> handleBaseException(BaseException e) {
        return R.fail(e.getCode(), e.getMessage());
    }
}
