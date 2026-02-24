package com.junoyi.module.payment.api.service;

import com.junoyi.module.payment.api.domain.entity.PaymentRecord;
import com.junoyi.module.payment.api.domain.vo.PaymentRecordVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 支付记录服务接口
 * 定义支付记录的业务操作方法，包括查询、创建、更新、退款等功能
 * 继承自 MyBatis-Plus 的 IService，提供基础的 CRUD 操作
 */
public interface IPaymentRecordService extends IService<PaymentRecord> {
    /**
     * 查询支付记录列表
     * 根据条件查询支付记录，并返回包含支付记录详细信息的VO列表
     * @param paymentRecord 查询条件
     * @return 支付记录VO列表
     */
    List<PaymentRecordVO> selectPaymentRecordList(PaymentRecord paymentRecord);

    /**
     * 根据ID查询支付记录信息
     * 根据支付记录ID查询支付记录的详细信息
     * @param id 支付记录ID
     * @return 支付记录VO
     */
    PaymentRecordVO selectPaymentRecordById(Long id);

    /**
     * 根据家长ID查询支付记录列表
     * 根据家长ID查询该家长的所有支付记录
     * @param parentId 家长ID
     * @return 支付记录VO列表
     */
    List<PaymentRecordVO> selectPaymentRecordListByParentId(Long parentId);

    /**
     * 根据组织者ID查询支付记录列表
     * 根据组织者ID查询该组织者的所有支付记录
     * @param organizerId 组织者ID
     * @return 支付记录VO列表
     */
    List<PaymentRecordVO> selectPaymentRecordListByOrganizerId(Long organizerId);

    /**
     * 创建支付记录
     * 创建新的支付记录
     * @param paymentRecord 支付记录实体
     * @return 支付记录实体
     */
    PaymentRecord createPayment(PaymentRecord paymentRecord);

    /**
     * 完成支付
     * 根据支付记录ID和交易ID，将支付状态标记为已支付
     * @param id 支付记录ID
     * @param transactionId 交易ID
     * @return 是否成功
     */
    boolean completePayment(Long id, String transactionId);

    /**
     * 退款操作
     * 根据支付记录ID和退款原因，将支付状态标记为已退款
     * @param id 支付记录ID
     * @param reason 退款原因
     * @return 是否成功
     */
    boolean refundPayment(Long id, String reason);
}
