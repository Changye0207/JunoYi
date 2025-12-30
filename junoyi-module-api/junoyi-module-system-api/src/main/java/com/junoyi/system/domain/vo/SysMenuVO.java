package com.junoyi.system.domain.vo;

import lombok.Data;

/**
 * 菜单返回响应数据传输实体
 *
 * @author Fan
 */
@Data
public class SysMenuVO {

    private Long id;

    private String name;

    // TODO:

    private SysMenuVO children;
}