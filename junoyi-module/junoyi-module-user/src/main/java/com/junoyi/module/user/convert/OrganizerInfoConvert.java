package com.junoyi.module.user.convert;

import com.junoyi.module.user.api.domain.entity.OrganizerInfo;
import com.junoyi.module.user.api.domain.vo.OrganizerInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface OrganizerInfoConvert {
    OrganizerInfoConvert INSTANCE = Mappers.getMapper(OrganizerInfoConvert.class);

    OrganizerInfoVO toVO(OrganizerInfo organizerInfo);
    List<OrganizerInfoVO> toVOList(List<OrganizerInfo> organizerInfoList);
}
