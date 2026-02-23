package com.junoyi.module.user.api.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PlatformAdminVO {
    private Long id;
    private Long userId;
    private String realName;
    private String phone;
    private String avatar;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
