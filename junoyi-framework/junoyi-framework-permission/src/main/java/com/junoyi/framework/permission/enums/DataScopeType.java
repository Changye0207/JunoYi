package com.junoyi.framework.permission.enums;


/**
 * 数据范围类型枚举
 * 定义了数据访问范围的类型，用于控制用户或系统组件对数据的访问权限范围
 *
 * @author Fan
 */
public enum DataScopeType {

    /**
     * 全部数据
     */
    ALL("all", "全部数据"),

    /**
     * 本部门数据
     */
    DEPT("dept", "本部门数据"),

    /**
     * 本部门及子部门数据
     */
    DEPT_AND_CHILD("dept_and_child", "本部门及子部门数据"),

    /**
     * 仅本人数据
     */
    SELF("self", "仅本人数据"),

    /**
     * 自定义数据范围
     */
    CUSTOM("custom", "自定义数据范围"),

    /**
     * 无数据权限
     */
    NONE("none", "无数据权限");

    private final String code;
    private final String description;

    DataScopeType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据 code 获取枚举
     */
    public static DataScopeType fromCode(String code) {
        for (DataScopeType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return NONE;
    }
}
