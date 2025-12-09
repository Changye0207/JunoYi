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
 * SQL 美化输出拦截器
 * 将 SQL 语句格式化后输出到日志，方便调试
 *
 * @author Fan
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
public class SqlBeautifyInterceptor implements Interceptor {

    private final JunoYiLog log = JunoYiLogFactory.getLogger(SqlBeautifyInterceptor.class);

    /**
     * 拦截方法，在执行 SQL 前对 SQL 进行美化并记录日志
     *
     * @param invocation 调用信息对象，包含目标对象和方法参数等
     * @return 执行结果
     * @throws Throwable 抛出异常
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();

        // 美化 SQL 并打印日志
        String beautifiedSql = beautifySql(sql);

        log.debug("Executing SQL:\n{}", beautifiedSql);
        log.debug("Parameters: {}", boundSql.getParameterObject());

        return invocation.proceed();
    }

    /**
     * 对原始 SQL 语句进行美化处理，提升可读性
     *
     * @param sql 原始 SQL 字符串
     * @return 美化后的 SQL 字符串
     */
    private String beautifySql(String sql) {
        if (sql == null || sql.trim().isEmpty()) {
            return sql;
        }

        // 移除多余空格，统一为单个空格
        sql = sql.replaceAll("\\s+", " ").trim();

        // 在关键字前添加换行符以实现基本的格式化
        sql = sql.replaceAll("(?i)\\b(SELECT|FROM|WHERE|AND|OR|ORDER BY|GROUP BY|HAVING|LIMIT|OFFSET|JOIN|LEFT JOIN|RIGHT JOIN|INNER JOIN|ON|AS|INSERT INTO|VALUES|UPDATE|SET|DELETE|CREATE|ALTER|DROP|TRUNCATE)\\b", "\n$1");

        // 若首字符是换行符则去除
        if (sql.startsWith("\n")) {
            sql = sql.substring(1);
        }

        return sql;
    }

    /**
     * 包装插件逻辑的目标对象
     *
     * @param target 目标对象
     * @return 插件代理对象
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置插件属性（当前未使用）
     *
     * @param properties 属性配置
     */
    @Override
    public void setProperties(Properties properties) {
        // 可以通过配置文件设置属性
    }
}
