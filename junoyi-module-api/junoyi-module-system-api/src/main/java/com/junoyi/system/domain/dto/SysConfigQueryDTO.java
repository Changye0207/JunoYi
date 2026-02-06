package com.junoyi.system.domain.dto;

import com.junoyi.framework.core.domain.page.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统参数查询DTO
 *
 * @author Fan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysConfigQueryDTO extends PageQuery {

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 系统内置（Y是 N否）
     */
    private String configType;
}
