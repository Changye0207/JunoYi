package com.junoyi.module.growth.api.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GrowthAttachmentVO {
    private Long id;
    private Long recordId;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private String fileUrl;
    private Integer sort;
    private LocalDateTime createTime;
}
