package com.junoyi.system.service;

import com.junoyi.system.domain.vo.RedisInfoVO;

/**
 * 系统缓存服务接口
 *
 * @author Fan
 */
public interface ISysCacheService {

    /**
     * 获取 Redis 服务器信息
     *
     * @return Redis 信息
     */
    RedisInfoVO getRedisInfo();
}
