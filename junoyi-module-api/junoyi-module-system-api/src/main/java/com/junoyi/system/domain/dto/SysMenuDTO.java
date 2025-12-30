package com.junoyi.system.domain.dto;

import lombok.Data;

/**
 * 菜单查询参数传输数据实体
 *
 * @author Fan
 */
@Data
public class SysMenuDTO {

    private Long id;

    private String name;

    private String path;

    private Integer status;
}