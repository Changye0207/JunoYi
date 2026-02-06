package com.junoyi.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 系统参数DTO
 *
 * @author Fan
 */
@Data
public class SysConfigDTO {

    /**
     * 参数ID（修改时必填）
     */
    @JsonProperty("id")
    private Long settingId;

    /**
     * 参数键名
     */
    @JsonProperty("configKey")
    private String settingKey;

    /**
     * 参数键值
     */
    @JsonProperty("configValue")
    private String settingValue;

    /**
     * 参数名称
     */
    @JsonProperty("configName")
    private String settingName;

    /**
     * 系统内置（Y是 N否）
     */
    @JsonProperty("configType")
    private String configType;

    /**
     * 备注
     */
    private String remark;
}
