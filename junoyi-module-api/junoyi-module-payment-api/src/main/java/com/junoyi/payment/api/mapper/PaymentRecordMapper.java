package com.junoyi.module.payment.api.mapper;

import com.junoyi.module.payment.api.domain.entity.PaymentRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRecordMapper extends BaseMapper<PaymentRecord> {
}
