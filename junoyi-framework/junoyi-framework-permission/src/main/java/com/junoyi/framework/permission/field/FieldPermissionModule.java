package com.junoyi.framework.permission.field;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 字段权限 Jackson 模块
 * <p>
 * 注册字段权限序列化修改器到 Jackson
 *
 * @author Fan
 */
public class FieldPermissionModule extends SimpleModule {

    public FieldPermissionModule() {
        super("FieldPermissionModule");
        setSerializerModifier(new FieldPermissionBeanSerializerModifier());
    }
}
