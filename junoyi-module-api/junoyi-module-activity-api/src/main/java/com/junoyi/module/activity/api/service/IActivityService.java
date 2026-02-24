package com.junoyi.activity.api.service;

import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 活动服务接口
 * 定义活动相关的业务操作方法，包括查询、创建、更新、删除、发布等功能
 * 继承自 MyBatis-Plus 的 IService，提供基础的 CRUD 操作
 */
public interface IActivityService extends IService<Activity> {
    /**
     * 查询活动列表
     * 根据条件查询活动列表，并返回包含活动详细信息的VO列表
     * @param activity 查询条件
     * @return 活动VO列表
     */
    List<ActivityVO> selectActivityList(Activity activity);

    /**
     * 根据ID查询活动
     * 根据活动ID查询活动的详细信息
     * @param id 活动ID
     * @return 活动VO
     */
    ActivityVO selectActivityById(Long id);

    /**
     * 查询组织者的活动列表
     * 根据组织者ID查询该组织者发布的所有活动
     * @param organizerId 组织者ID
     * @return 活动VO列表
     */
    List<ActivityVO> selectActivityListByOrganizerId(Long organizerId);

    /**
     * 新增活动
     * 创建一条新的活动记录
     * @param activity 活动实体
     * @return 影响行数
     */
    int insertActivity(Activity activity);

    /**
     * 更新活动
     * 更新活动的基本信息
     * @param activity 活动实体
     * @return 影响行数
     */
    int updateActivity(Activity activity);

    /**
     * 发布活动
     * 将活动状态设置为已发布
     * @param id 活动ID
     * @return 影响行数
     */
    int publishActivity(Long id);

    /**
     * 下线活动
     * 将活动状态设置为已下线
     * @param id 活动ID
     * @return 影响行数
     */
    int offlineActivity(Long id);

    /**
     * 检查活动是否可用
     * 检查活动是否处于可报名状态
     * @param id 活动ID
     * @return 是否可用
     */
    boolean checkActivityAvailable(Long id);

    /**
     * 增加活动参与者数量
     * 活动报名成功后，增加当前参与人数
     * @param id 活动ID
     * @return 是否成功
     */
    boolean incrementParticipants(Long id);

    /**
     * 减少活动参与者数量
     * 活动取消报名后，减少当前参与人数
     * @param id 活动ID
     * @return 是否成功
     */
    boolean decrementParticipants(Long id);

    /**
     * 增加活动浏览次数
     * 活动被浏览时，增加浏览次数
     * @param id 活动ID
     * @return 是否成功
     */
    boolean incrementViewCount(Long id);
}
