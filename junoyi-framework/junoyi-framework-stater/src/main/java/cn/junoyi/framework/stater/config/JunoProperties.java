package cn.junoyi.framework.stater.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Juno配置参数类
 *
 * @author Fan
 */
@Component
@ConfigurationProperties(prefix = "juno")
public class JunoProperties {

    /**
     * JunoYi 框架版本
     */
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}