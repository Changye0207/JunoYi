package com.junoyi.framework.datasource.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据源配置属性
 *
 * @author Fan
 */
@Data
@ConfigurationProperties(prefix = "junoyi.datasource")
public class DataSourceProperties {

    /**
     * 是否启用 SQL 美化输出
     */
    private boolean sqlBeautifyEnabled = true;

    /**
     * 是否启用慢 SQL 监控
     */
    private boolean slowSqlEnabled = true;

    /**
     * 慢 SQL 阈值（毫秒）
     */
    private long slowSqlThreshold = 3000;

    /**
     * 是否启用 SQL 日志输出
     */
    private boolean sqlLogEnabled = true;
}
