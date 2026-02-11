package com.junoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.framework.core.utils.DateUtils;
import com.junoyi.framework.core.utils.StringUtils;
import com.junoyi.framework.log.core.JunoYiLog;
import com.junoyi.framework.log.core.JunoYiLogFactory;
import com.junoyi.framework.security.utils.SecurityUtils;
import com.junoyi.system.convert.SysDictTypeConverter;
import com.junoyi.system.domain.dto.SysDictTypeDTO;
import com.junoyi.system.domain.dto.SysDictTypeQueryDTO;
import com.junoyi.system.domain.po.SysDictType;
import com.junoyi.system.domain.vo.SysDictTypeVO;
import com.junoyi.system.mapper.SysDictTypeMapper;
import com.junoyi.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典类型服务实现类
 *
 * @author Fan
 */
@Service
@RequiredArgsConstructor
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    private final JunoYiLog log = JunoYiLogFactory.getLogger(SysDictTypeServiceImpl.class);
    private final SysDictTypeMapper sysDictTypeMapper;
    private final SysDictTypeConverter sysDictTypeConverter;

    /**
     * 分页查询字典类型列表
     *
     * @param queryDTO 查询条件DTO
     * @return 分页结果对象
     */
    @Override
    public PageResult<SysDictTypeVO> getDictTypeList(SysDictTypeQueryDTO queryDTO) {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(queryDTO.getDictName()), SysDictType::getDictName, queryDTO.getDictName())
                .like(StringUtils.isNotBlank(queryDTO.getDictType()), SysDictType::getDictType, queryDTO.getDictType())
                .eq(StringUtils.isNotBlank(queryDTO.getStatus()), SysDictType::getStatus, queryDTO.getStatus())
                .orderByDesc(SysDictType::getCreateTime);

        Page<SysDictType> page = new Page<>(queryDTO.getCurrent(), queryDTO.getSize());
        Page<SysDictType> resultPage = sysDictTypeMapper.selectPage(page, wrapper);

        List<SysDictTypeVO> voList = sysDictTypeConverter.toVoList(resultPage.getRecords());
        return PageResult.of(voList, resultPage.getTotal(), (int) resultPage.getCurrent(), (int) resultPage.getSize());
    }

    /**
     * 查询所有字典类型
     *
     * @return 字典类型列表
     */
    @Override
    public List<SysDictTypeVO> getAllDictTypes() {
        LambdaQueryWrapper<SysDictType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysDictType::getStatus, "0")
                .orderByAsc(SysDictType::getDictType);

        List<SysDictType> dictTypes = sysDictTypeMapper.selectList(wrapper);
        return sysDictTypeConverter.toVoList(dictTypes);
    }

    /**
     * 根据ID查询字典类型详情
     *
     * @param dictId 字典类型ID
     * @return 字典类型VO对象
     */
    @Override
    public SysDictTypeVO getDictTypeById(Long dictId) {
        SysDictType dictType = sysDictTypeMapper.selectById(dictId);
        return dictType != null ? sysDictTypeConverter.toVo(dictType) : null;
    }

    /**
     * 新增字典类型
     *
     * @param dictTypeDTO 字典类型DTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDictType(SysDictTypeDTO dictTypeDTO) {
        SysDictType dictType = sysDictTypeConverter.toEntity(dictTypeDTO);

        // 设置默认值
        if (dictType.getStatus() == null) {
            dictType.setStatus("0");
        }
        dictType.setCreateBy(SecurityUtils.getUserName());
        dictType.setCreateTime(DateUtils.getNowDate());

        sysDictTypeMapper.insert(dictType);

        log.info("DictType", "添加字典类型: {}", dictType.getDictType());
    }

    /**
     * 修改字典类型
     *
     * @param dictTypeDTO 字典类型DTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictType(SysDictTypeDTO dictTypeDTO) {
        SysDictType oldDictType = sysDictTypeMapper.selectById(dictTypeDTO.getDictId());
        if (oldDictType == null) {
            throw new RuntimeException("字典类型不存在");
        }

        SysDictType dictType = sysDictTypeConverter.toEntity(dictTypeDTO);
        dictType.setDictId(dictTypeDTO.getDictId());

        // 保留原有的字段值
        if (dictType.getStatus() == null) {
            dictType.setStatus(oldDictType.getStatus());
        }
        dictType.setUpdateBy(SecurityUtils.getUserName());
        dictType.setUpdateTime(DateUtils.getNowDate());

        sysDictTypeMapper.updateById(dictType);

        log.info("DictType", "更新字典类型: {}", dictType.getDictType());
    }

    /**
     * 删除字典类型
     *
     * @param dictId 字典类型ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictType(Long dictId) {
        SysDictType dictType = sysDictTypeMapper.selectById(dictId);
        if (dictType == null) {
            throw new RuntimeException("字典类型不存在");
        }

        sysDictTypeMapper.deleteById(dictId);

        log.info("DictType", "删除字典类型: {}", dictType.getDictType());
    }

    /**
     * 批量删除字典类型
     *
     * @param dictIds 字典类型ID列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictTypes(List<Long> dictIds) {
        // 批量删除
        for (Long dictId : dictIds) {
            sysDictTypeMapper.deleteById(dictId);
        }

        log.info("DictType", "批量删除字典类型: {} 条", dictIds.size());
    }
}
