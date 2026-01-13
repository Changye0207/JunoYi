package com.junoyi.framework.datasource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据源配置属性
 *
 * @author Fan
 */
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

    /**
     * 数据范围配置
     */
    private DataScopeConfig dataScope = new DataScopeConfig();

    public boolean isSqlBeautifyEnabled() {
        return sqlBeautifyEnabled;
    }

    public void setSqlBeautifyEnabled(boolean sqlBeautifyEnabled) {
        this.sqlBeautifyEnabled = sqlBeautifyEnabled;
    }

    public boolean isSlowSqlEnabled() {
        return slowSqlEnabled;
    }

    public void setSlowSqlEnabled(boolean slowSqlEnabled) {
        this.slowSqlEnabled = slowSqlEnabled;
    }

    public long getSlowSqlThreshold() {
        return slowSqlThreshold;
    }

    public void setSlowSqlThreshold(long slowSqlThreshold){
        this.slowSqlThreshold = slowSqlThreshold;
    }

    public boolean isSqlLogEnabled() {
        return sqlLogEnabled;
    }

    public void setSqlLogEnabled(boolean sqlLogEnabled) {
        this.sqlLogEnabled = sqlLogEnabled;
    }

    public DataScopeConfig getDataScope() {
        return dataScope;
    }

    public void setDataScope(DataScopeConfig dataScope) {
        this.dataScope = dataScope;
    }

    /**
     * 数据范围配置
     */
    public static class DataScopeConfig {

        /**
         * 是否启用数据范围
         */
        private boolean enabled = true;

        /**
         * 是否启用全局模式（对所有查询生效）
         * false: 仅对标注 @DataScope 的方法生效
         * true: 对所有查询生效
         */
        private boolean globalEnabled = false;

        /**
         * 默认部门字段名
         */
        private String defaultDeptField = "dept_id";

        /**
         * 默认用户字段名
         */
        private String defaultUserField = "create_by";

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isGlobalEnabled() {
            return globalEnabled;
        }

        public void setGlobalEnabled(boolean globalEnabled) {
            this.globalEnabled = globalEnabled;
        }

        public String getDefaultDeptField() {
            return defaultDeptField;
        }

        public void setDefaultDeptField(String defaultDeptField) {
            this.defaultDeptField = defaultDeptField;
        }

        public String getDefaultUserField() {
            return defaultUserField;
        }

        public void setDefaultUserField(String defaultUserField) {
            this.defaultUserField = defaultUserField;
        }
    }
}
