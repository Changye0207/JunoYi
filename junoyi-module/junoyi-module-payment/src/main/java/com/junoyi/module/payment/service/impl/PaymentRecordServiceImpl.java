package com.junoyi.module.payment.service.impl;

import com.junoyi.module.payment.api.domain.entity.PaymentRecord;
import com.junoyi.module.payment.api.domain.vo.PaymentRecordVO;
import com.junoyi.module.payment.api.mapper.PaymentRecordMapper;
import com.junoyi.module.payment.api.service.IPaymentRecordService;
import com.junoyi.module.payment.convert.PaymentRecordConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord>
        implements IPaymentRecordService {

    @Override
    public List<PaymentRecordVO> selectPaymentRecordList(PaymentRecord paymentRecord) {
        LambdaQueryWrapper<PaymentRecord> wrapper = new LambdaQueryWrapper<>();
        if (paymentRecord.getParentId() != null) {
            wrapper.eq(PaymentRecord::getParentId, paymentRecord.getParentId());
        }
        if (paymentRecord.getOrganizerId() != null) {
            wrapper.eq(PaymentRecord::getOrganizerId, paymentRecord.getOrganizerId());
        }
        if (paymentRecord.getPaymentStatus() != null) {
            wrapper.eq(PaymentRecord::getPaymentStatus, paymentRecord.getPaymentStatus());
        }
        if (StringUtils.hasText(paymentRecord.getPaymentNo())) {
            wrapper.like(PaymentRecord::getPaymentNo, paymentRecord.getPaymentNo());
        }
        wrapper.orderByDesc(PaymentRecord::getCreateTime);
        List<PaymentRecord> list = list(wrapper);
        return PaymentRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    public PaymentRecordVO selectPaymentRecordById(Long id) {
        PaymentRecord paymentRecord = getById(id);
        return PaymentRecordConvert.INSTANCE.toVO(paymentRecord);
    }

    @Override
    public List<PaymentRecordVO> selectPaymentRecordListByParentId(Long parentId) {
        LambdaQueryWrapper<PaymentRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PaymentRecord::getParentId, parentId);
        wrapper.orderByDesc(PaymentRecord::getCreateTime);
        List<PaymentRecord> list = list(wrapper);
        return PaymentRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    public List<PaymentRecordVO> selectPaymentRecordListByOrganizerId(Long organizerId) {
        LambdaQueryWrapper<PaymentRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PaymentRecord::getOrganizerId, organizerId);
        wrapper.orderByDesc(PaymentRecord::getCreateTime);
        List<PaymentRecord> list = list(wrapper);
        return PaymentRecordConvert.INSTANCE.toVOList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentRecord createPayment(PaymentRecord paymentRecord) {
        paymentRecord.setPaymentNo(generatePaymentNo());
        paymentRecord.setPaymentStatus(0);
        save(paymentRecord);
        return paymentRecord;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean completePayment(Long id, String transactionId) {
        PaymentRecord paymentRecord = getById(id);
        if (paymentRecord == null) {
            throw new RuntimeException("支付记录不存在");
        }
        if (paymentRecord.getPaymentStatus() != 0) {
            throw new RuntimeException("当前状态不可完成支付");
        }

        paymentRecord.setPaymentStatus(1);
        paymentRecord.setTransactionId(transactionId);
        paymentRecord.setPaymentTime(LocalDateTime.now());
        return updateById(paymentRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean refundPayment(Long id, String reason) {
        PaymentRecord paymentRecord = getById(id);
        if (paymentRecord == null) {
            throw new RuntimeException("支付记录不存在");
        }
        if (paymentRecord.getPaymentStatus() != 1) {
            throw new RuntimeException("当前状态不可退款");
        }

        paymentRecord.setPaymentStatus(2);
        paymentRecord.setRefundTime(LocalDateTime.now());
        paymentRecord.setRefundReason(reason);
        return updateById(paymentRecord);
    }

    private String generatePaymentNo() {
        return "PAY" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
