package com.junoyi.framework.web.config;

import com.junoyi.framework.web.filter.XssFilter;
import com.junoyi.framework.web.properties.XssProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web 模块配置
 *
 * @author Fan
 */
@Configuration
@EnableConfigurationProperties(XssProperties.class)
public class WebConfiguration {

    /**
     * XSS 过滤器
     */
    @Bean
    @ConditionalOnProperty(prefix = "xss", name = "enable", havingValue = "true", matchIfMissing = true)
    public FilterRegistrationBean<XssFilter> xssFilterRegistration(XssProperties xssProperties) {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new XssFilter(xssProperties));
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        registration.setOrder(1);
        return registration;
    }
}
