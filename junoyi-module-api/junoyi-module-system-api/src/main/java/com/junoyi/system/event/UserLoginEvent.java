package com.junoyi.system.event;

import com.junoyi.framework.event.domain.BaseEvent;
import com.junoyi.system.enums.PlatformType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户登录事件
 * 当用户进行登录时候触发该事件
 *
 * @author Fan
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserLoginEvent extends BaseEvent {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录平台类型
     */
    private PlatformType platformType;

    /**
     * 登录平台唯一标识符ID
     */
    private String platformUID;

    /**
     * 使用的token
     */
    private String token;
}