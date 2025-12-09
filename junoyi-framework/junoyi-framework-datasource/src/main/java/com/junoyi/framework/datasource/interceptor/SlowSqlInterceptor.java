package com.junoyi.framework.datasource.interceptor;

import com.junoyi.framework.log.core.JunoYiLog;
import com.junoyi.framework.log.core.JunoYiLogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * 慢 SQL 监控拦截器
 * 记录执行时间超过阈值的 SQL 语句
 *
 * @author Fan
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
public class SlowSqlInterceptor implements Interceptor {

    private final JunoYiLog log = JunoYiLogFactory.getLogger(SlowSqlInterceptor.class);

    /**
     * 慢 SQL 阈值（毫秒），默认 3 秒
     */
    private long slowSqlThreshold = 3000;

    /**
     * 拦截方法，在 SQL 执行前后进行计时，并判断是否为慢 SQL。
     * 如果执行时间超过设定阈值，则记录警告日志。
     *
     * @param invocation MyBatis 调用上下文对象，包含目标方法和参数信息
     * @return 原始方法调用结果
     * @throws Throwable 方法执行过程中可能抛出的异常
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            // 如果执行时间超过阈值，记录慢 SQL
            if (executionTime >= slowSqlThreshold) {
                StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
                BoundSql boundSql = statementHandler.getBoundSql();
                String sql = boundSql.getSql();

                log.warn("Slow SQL detected! Execution time: {} ms\nSQL: {}\nParameters: {}",
                        executionTime, sql.replaceAll("\\s+", " "), boundSql.getParameterObject());
            }
        }
    }

    /**
     * 包装目标对象，生成代理对象以应用当前拦截器逻辑。
     *
     * @param target 被代理的目标对象
     * @return 应用拦截器后的代理对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置属性配置，用于从外部配置中读取慢 SQL 的阈值。
     *
     * @param properties 外部传入的属性集合，应包含键为 slowSqlThreshold 的配置项
     */
    @Override
    public void setProperties(Properties properties) {
        // 从配置文件读取慢 SQL 阈值
        String threshold = properties.getProperty("slowSqlThreshold");
        if (threshold != null && !threshold.trim().isEmpty()) {
            this.slowSqlThreshold = Long.parseLong(threshold);
        }
    }

    /**
     * 设置慢 SQL 阈值（单位：毫秒）
     *
     * @param slowSqlThreshold 新的慢 SQL 阈值
     */
    public void setSlowSqlThreshold(long slowSqlThreshold) {
        this.slowSqlThreshold = slowSqlThreshold;
    }
}
