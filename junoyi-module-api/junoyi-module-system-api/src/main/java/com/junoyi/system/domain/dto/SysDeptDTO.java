package com.junoyi.system.domain.dto;

import lombok.Data;

/**
 * 系统部门数据传输对象
 * 用于封装系统部门相关的信息，提供部门数据的传输和存储功能
 *
 * @author Fan
 */
@Data
public class SysDeptDTO {

    /**
     * 部门ID，唯一标识一个部门
     */
    private Long id;

    /**
     * 父部门ID，用于构建部门层级关系
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序号，用于部门列表的排序显示
     */
    private Integer sort;

    /**
     * 部门负责人姓名
     */
    private String leader;

    /**
     * 负责人联系电话
     */
    private String phonenumber;

    /**
     * 负责人邮箱地址
     */
    private String email;

    /**
     * 备注信息，用于存储部门相关的额外说明
     */
    private String remark;
}
