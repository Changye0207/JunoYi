package com.junoyi.framework.permission.field;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.junoyi.framework.permission.annotation.FieldPermission;

import java.util.List;

/**
 * Bean 序列化修改器
 * <p>
 * 扫描带有 @FieldPermission 注解的字段，替换其序列化器
 *
 * @author Fan
 */
public class FieldPermissionBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                      BeanDescription beanDesc,
                                                      List<BeanPropertyWriter> beanProperties) {
        for (BeanPropertyWriter writer : beanProperties) {
            FieldPermission annotation = writer.getAnnotation(FieldPermission.class);
            if (annotation != null) {
                writer.assignSerializer(new FieldPermissionSerializer(annotation));
            }
        }
        return beanProperties;
    }
}
