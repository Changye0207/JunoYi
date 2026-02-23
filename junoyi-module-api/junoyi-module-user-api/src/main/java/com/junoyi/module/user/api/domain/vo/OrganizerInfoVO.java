package com.junoyi.module.user.api.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrganizerInfoVO {
    private Long id;
    private Long userId;
    private String name;
    private String contactPerson;
    private String contactPhone;
    private String address;
    private String businessLicense;
    private String description;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
