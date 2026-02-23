package com.junoyi.module.user.convert;

import com.junoyi.module.user.api.domain.entity.ParentInfo;
import com.junoyi.module.user.api.domain.vo.ParentInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ParentInfoConvert {
    ParentInfoConvert INSTANCE = Mappers.getMapper(ParentInfoConvert.class);

    ParentInfoVO toVO(ParentInfo parentInfo);
    List<ParentInfoVO> toVOList(List<ParentInfo> parentInfoList);
}
