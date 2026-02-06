package com.junoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.framework.core.utils.StringUtils;
import com.junoyi.framework.log.core.JunoYiLog;
import com.junoyi.framework.log.core.JunoYiLogFactory;
import com.junoyi.framework.redis.utils.RedisUtils;
import com.junoyi.system.convert.SysConfigConverter;
import com.junoyi.system.domain.dto.SysConfigDTO;
import com.junoyi.system.domain.dto.SysConfigQueryDTO;
import com.junoyi.system.domain.po.SysConfig;
import com.junoyi.system.domain.vo.SysConfigVO;
import com.junoyi.system.mapper.SysConfigMapper;
import com.junoyi.system.service.ISysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统参数配置业务实现类
 *
 * @author Fan
 */
@Service
@RequiredArgsConstructor
public class SysConfigServiceImpl implements ISysConfigService {

    private final JunoYiLog log = JunoYiLogFactory.getLogger(SysConfigServiceImpl.class);
    private final SysConfigMapper sysConfigMapper;
    private final SysConfigConverter sysConfigConverter;

    private static final String CACHE_KEY_PREFIX = "sys:config:";

    @Override
    public PageResult<SysConfigVO> getConfigList(SysConfigQueryDTO queryDTO) {
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(queryDTO.getConfigName()), SysConfig::getSettingName, queryDTO.getConfigName())
                .like(StringUtils.isNotBlank(queryDTO.getConfigKey()), SysConfig::getSettingKey, queryDTO.getConfigKey())
                .eq(StringUtils.isNotBlank(queryDTO.getConfigType()), SysConfig::getIsSystem, "Y".equals(queryDTO.getConfigType()) ? 1 : 0)
                .orderByAsc(SysConfig::getSort)
                .orderByDesc(SysConfig::getCreateTime);

        Page<SysConfig> page = new Page<>(queryDTO.getCurrent(), queryDTO.getSize());
        Page<SysConfig> resultPage = sysConfigMapper.selectPage(page, wrapper);
        
        List<SysConfigVO> voList = sysConfigConverter.toVoList(resultPage.getRecords());
        return PageResult.of(voList, resultPage.getTotal(), (int) resultPage.getCurrent(), (int) resultPage.getSize());
    }

    @Override
    public SysConfigVO getConfigById(Long id) {
        SysConfig config = sysConfigMapper.selectById(id);
        return config != null ? sysConfigConverter.toVo(config) : null;
    }

    @Override
    public String getConfigByKey(String configKey) {
        // 先从缓存获取
        String cacheKey = CACHE_KEY_PREFIX + configKey;
        String cachedValue = RedisUtils.getCacheObject(cacheKey);
        if (cachedValue != null) {
            return cachedValue;
        }

        // 缓存未命中，从数据库查询
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getSettingKey, configKey)
                .eq(SysConfig::getStatus, 0);
        SysConfig config = sysConfigMapper.selectOne(wrapper);

        if (config != null) {
            String value = config.getSettingValue();
            // 存入缓存（永久有效，手动刷新）
            RedisUtils.setCacheObject(cacheKey, value);
            return value;
        }

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addConfig(SysConfigDTO configDTO) {
        // 检查键名是否已存在
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysConfig::getSettingKey, configDTO.getSettingKey());
        Long count = sysConfigMapper.selectCount(wrapper);
        if (count > 0) {
            throw new IllegalArgumentException("参数键名已存在");
        }

        SysConfig config = sysConfigConverter.toEntity(configDTO);
        if (config.getStatus() == null) {
            config.setStatus(0); // 默认正常状态
        }
        if (config.getSort() == null) {
            config.setSort(0); // 默认排序
        }
        sysConfigMapper.insert(config);

        // 清除缓存
        clearCache(config.getSettingKey());
        log.info("Config", "添加系统参数: {}", config.getSettingKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(SysConfigDTO configDTO) {
        SysConfig oldConfig = sysConfigMapper.selectById(configDTO.getSettingId());
        if (oldConfig == null) {
            throw new IllegalArgumentException("参数不存在");
        }

        // 系统内置参数不允许修改键名
        if (oldConfig.getIsSystem() == 1 && !oldConfig.getSettingKey().equals(configDTO.getSettingKey())) {
            throw new IllegalArgumentException("系统内置参数不允许修改键名");
        }

        // 转换DTO到实体
        SysConfig config = sysConfigConverter.toEntity(configDTO);
        config.setSettingId(configDTO.getSettingId());
        
        // 保留原有的字段值（DTO中没有的字段）
        if (config.getSettingType() == null) {
            config.setSettingType(oldConfig.getSettingType());
        }
        if (config.getSettingGroup() == null) {
            config.setSettingGroup(oldConfig.getSettingGroup());
        }
        if (config.getSort() == null) {
            config.setSort(oldConfig.getSort());
        }
        if (config.getStatus() == null) {
            config.setStatus(oldConfig.getStatus());
        }
        
        sysConfigMapper.updateById(config);

        // 清除缓存
        clearCache(oldConfig.getSettingKey());
        if (!oldConfig.getSettingKey().equals(config.getSettingKey())) {
            clearCache(config.getSettingKey());
        }

        log.info("Config", "更新系统参数: {}", config.getSettingKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfig(Long id) {
        SysConfig config = sysConfigMapper.selectById(id);
        if (config == null) {
            throw new IllegalArgumentException("参数不存在");
        }

        // 系统内置参数不允许删除
        if (config.getIsSystem() == 1) {
            throw new IllegalArgumentException("系统内置参数不允许删除");
        }

        sysConfigMapper.deleteById(id);

        // 清除缓存
        clearCache(config.getSettingKey());
        log.info("Config", "删除系统参数: {}", config.getSettingKey());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigBatch(List<Long> ids) {
        // 检查是否包含系统内置参数
        LambdaQueryWrapper<SysConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysConfig::getSettingId, ids)
                .eq(SysConfig::getIsSystem, 1);
        Long count = sysConfigMapper.selectCount(wrapper);
        if (count > 0) {
            throw new IllegalArgumentException("不能删除系统内置参数");
        }

        // 获取所有配置的键名，用于清除缓存
        List<SysConfig> configs = sysConfigMapper.selectBatchIds(ids);

        // 批量删除
        for (Long id : ids) {
            sysConfigMapper.deleteById(id);
        }

        // 清除缓存
        configs.forEach(config -> clearCache(config.getSettingKey()));
        log.info("Config", "批量删除系统参数: {} 条", ids.size());
    }

    @Override
    public void refreshCache() {
        // 清除所有配置缓存
        RedisUtils.deleteKeys(CACHE_KEY_PREFIX + "*");
        log.info("Config", "刷新系统参数缓存");
    }

    /**
     * 清除指定键名的缓存
     */
    private void clearCache(String configKey) {
        String cacheKey = CACHE_KEY_PREFIX + configKey;
        RedisUtils.deleteObject(cacheKey);
    }
}
