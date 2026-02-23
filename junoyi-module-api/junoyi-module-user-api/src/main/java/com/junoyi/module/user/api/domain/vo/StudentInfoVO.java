package com.junoyi.module.user.api.domain.vo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentInfoVO {
    private Long id;
    private Long parentId;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String school;
    private String grade;
    private String avatar;
    private String remarks;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
