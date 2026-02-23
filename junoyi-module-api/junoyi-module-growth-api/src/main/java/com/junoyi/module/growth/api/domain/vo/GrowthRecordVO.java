package com.junoyi.module.growth.api.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GrowthRecordVO {
    private Long id;
    private Long parentId;
    private Long studentId;
    private Long activityId;
    private String title;
    private String content;
    private String recordType;
    private Integer viewCount;
    private Integer likeCount;
    private Integer status;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
