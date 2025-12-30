package com.junoyi.system.convert;

import com.junoyi.framework.core.convert.BaseConverter;
import com.junoyi.framework.core.convert.MapStructConfig;
import com.junoyi.system.domain.dto.SysMenuDTO;
import com.junoyi.system.domain.po.SysMenu;
import com.junoyi.system.domain.vo.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统菜单转换器接口
 * 用于SysMenu实体、DTO和VO之间的相互转换
 *
 * @author Fan
 */
@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface SysMenuConverter  extends BaseConverter<SysMenuDTO, SysMenu, SysMenuVO> {
    /**
     * 静态实例（用于非 Spring 环境或静态方法中）
     */
    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);


}

