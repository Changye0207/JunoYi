package cn.junoyi.framework.log;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * JunoYi框架Logger注解注入器
 * 检测@JunoYILogger注解，自动初始化已声明的log字段
 *
 * @author Fan
 */
@Component
public class JunoYiLoggerInjector implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        return null;
    }

    /**
     * 自动初始化已声明的log字段
     */
    private Object initializeLogField(Object bean, Class<?> beanClass) {
        return null;
    }
}
