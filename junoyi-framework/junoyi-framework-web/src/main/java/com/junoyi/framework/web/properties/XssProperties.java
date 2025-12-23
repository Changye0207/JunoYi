package com.junoyi.framework.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * XSS 防护配置属性
 *
 * @author Fan
 */
@Data
@ConfigurationProperties(prefix = "junoyi.web.xss")
public class XssProperties {

    /**
     * 是否启用 XSS 过滤
     */
    private boolean enable = true;

    /**
     * 排除的 URL 路径（支持 Ant 风格）
     */
    private List<String> excludeUrls = new ArrayList<>();

    /**
     * 排除的请求方法（如 GET）
     */
    private List<String> excludeMethods = new ArrayList<>();
}
