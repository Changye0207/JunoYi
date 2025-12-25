package com.junoyi.framework.permission.annotation;

import com.junoyi.framework.permission.enums.DataScopeType;

import java.lang.annotation.*;

/**
 * 数据范围注解
 * <p>
 * 用于控制数据行级别的访问权限，自动在 SQL 查询中注入数据范围条件
 * <p>
 * 使用示例：
 * <pre>
 * // 基于部门的数据范围
 * &#64;DataScope(permission = "system.user.view.row", scopeField = "dept_id")
 * public List&lt;User&gt; listUsers(UserQuery query) { ... }
 *
 * // 基于创建人的数据范围
 * &#64;DataScope(permission = "order.view.row", scopeField = "create_by", scopeType = DataScopeType.SELF)
 * public List&lt;Order&gt; listOrders(OrderQuery query) { ... }
 *
 * // 自定义 SQL 条件
 * &#64;DataScope(permission = "report.view.row", customSql = "region_id IN (SELECT region_id FROM user_region WHERE user_id = #{userId})")
 * public List&lt;Report&gt; listReports(ReportQuery query) { ... }
 * </pre>
 *
 * @author Fan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataScope {

    /**
     * 数据范围权限节点
     * <p>
     * 用于判断用户的数据范围类型，不同权限组可配置不同的数据范围策略
     */
    String permission() default "";

    /**
     * 数据范围类型
     * <p>
     * 当未配置 permission 或权限组未指定数据范围时，使用此默认值
     */
    DataScopeType scopeType() default DataScopeType.SELF;

    /**
     * 数据范围字段名
     * <p>
     * 用于 SQL 条件拼接的字段名，如 dept_id、create_by 等
     */
    String scopeField() default "dept_id";

    /**
     * 表别名
     * <p>
     * 多表关联查询时指定表别名，如 "u" 对应 "u.dept_id"
     */
    String tableAlias() default "";

    /**
     * 自定义 SQL 条件
     * <p>
     * 支持 SpEL 表达式，可使用以下变量：
     * <ul>
     *   <li>#{userId} - 当前用户ID</li>
     *   <li>#{deptId} - 当前用户部门ID</li>
     *   <li>#{deptIds} - 当前用户可访问的部门ID列表</li>
     *   <li>#{username} - 当前用户名</li>
     * </ul>
     */
    String customSql() default "";

    /**
     * 是否包含子部门数据
     * <p>
     * 仅在 scopeType 为 DEPT 时生效
     */
    boolean includeChildDept() default true;

    /**
     * 是否忽略超级管理员
     * <p>
     * true: 超级管理员也受数据范围限制
     * false: 超级管理员可查看所有数据
     */
    boolean ignoreSuperAdmin() default false;
}
