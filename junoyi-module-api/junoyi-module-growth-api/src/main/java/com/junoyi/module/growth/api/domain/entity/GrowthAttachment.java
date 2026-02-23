package com.junoyi.module.growth.api.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("growth_attachment")
public class GrowthAttachment {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long recordId;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private String fileUrl;
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    private Integer deleted;
}
