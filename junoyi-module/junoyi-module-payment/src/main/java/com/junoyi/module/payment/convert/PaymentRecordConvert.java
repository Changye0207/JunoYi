package com.junoyi.module.payment.convert;

import com.junoyi.module.payment.api.domain.entity.PaymentRecord;
import com.junoyi.module.payment.api.domain.vo.PaymentRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface PaymentRecordConvert {
    PaymentRecordConvert INSTANCE = Mappers.getMapper(PaymentRecordConvert.class);

    PaymentRecordVO toVO(PaymentRecord paymentRecord);
    List<PaymentRecordVO> toVOList(List<PaymentRecord> paymentRecordList);
}
