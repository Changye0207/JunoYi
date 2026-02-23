package com.junoyi.module.user.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("student_info")
public class StudentInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long parentId;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String school;
    private String grade;
    private String avatar;
    private String remarks;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
