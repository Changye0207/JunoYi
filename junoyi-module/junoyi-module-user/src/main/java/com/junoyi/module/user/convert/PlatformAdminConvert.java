package com.junoyi.module.user.convert;

import com.junoyi.module.user.api.domain.entity.PlatformAdmin;
import com.junoyi.module.user.api.domain.vo.PlatformAdminVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface PlatformAdminConvert {
    PlatformAdminConvert INSTANCE = Mappers.getMapper(PlatformAdminConvert.class);

    PlatformAdminVO toVO(PlatformAdmin platformAdmin);
    List<PlatformAdminVO> toVOList(List<PlatformAdmin> platformAdminList);
}
