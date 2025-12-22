package com.junoyi.framework.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类，用于自定义Spring MVC的配置
 *
 * <p>该类实现了WebMvcConfigurer接口，可以重写其中的方法来自定义Spring MVC的各种配置，
 * 如拦截器、视图解析器、静态资源处理等。</p>
 *
 * <p>使用{@link @AutoConfiguration}注解标识这是一个自动配置类，
 * Spring Boot会自动加载此类来配置Web MVC相关功能。</p>
 *
 * <p>使用{@link @RequiredArgsConstructor}注解自动生成包含所有final字段的构造函数，
 * 用于依赖注入。</p>
 *
 * @author Fan
 */
@AutoConfiguration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer  {


}
