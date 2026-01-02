package com.junoyi.framework.core.exception.dept;

/**
 * 部门包含子部门异常类
 * 当部门存在子部门时抛出此异常，继承自DeptException
 *
 * @author Fan
 */
public class DeptHasChildrenException extends DeptException {

    /**
     * 构造函数
     * @param message 异常描述信息
     */
    public DeptHasChildrenException(String message) {
        super(501, message, "HAS_CHILDREN");
    }

    /**
     * 获取领域前缀
     * @return 返回父类的领域前缀
     */
    @Override
    public String getDomainPrefix() {
        return super.getDomainPrefix();
    }
}
