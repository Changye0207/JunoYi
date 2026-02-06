package com.junoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统参数VO
 *
 * @author Fan
 */
@Data
public class SysConfigVO {

    /**
     * 参数ID
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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
