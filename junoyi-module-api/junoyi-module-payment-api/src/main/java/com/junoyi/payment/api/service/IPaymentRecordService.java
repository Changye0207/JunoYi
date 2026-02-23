package com.junoyi.module.payment.api.service;

import com.junoyi.module.payment.api.domain.entity.PaymentRecord;
import com.junoyi.module.payment.api.domain.vo.PaymentRecordVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface IPaymentRecordService extends IService<PaymentRecord> {
    List<PaymentRecordVO> selectPaymentRecordList(PaymentRecord paymentRecord);
    PaymentRecordVO selectPaymentRecordById(Long id);
    List<PaymentRecordVO> selectPaymentRecordListByParentId(Long parentId);
    List<PaymentRecordVO> selectPaymentRecordListByOrganizerId(Long organizerId);
    PaymentRecord createPayment(PaymentRecord paymentRecord);
    boolean completePayment(Long id, String transactionId);
    boolean refundPayment(Long id, String reason);
}
