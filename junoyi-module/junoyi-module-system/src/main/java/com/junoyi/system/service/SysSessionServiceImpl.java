package com.junoyi.system.service;

import com.junoyi.framework.core.domain.page.PageQuery;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.framework.core.utils.StringUtils;
import com.junoyi.framework.security.helper.SessionHelper;
import com.junoyi.framework.security.module.UserSession;
import com.junoyi.system.domain.dto.SysSessionQueryDTO;
import com.junoyi.system.domain.po.SysSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统会话服务实现
 *
 * @author Fan
 */
@Service
@RequiredArgsConstructor
public class SysSessionServiceImpl implements ISysSessionService {

    private final SessionHelper sessionHelper;

    @Override
    public PageResult<SysSession> getSessionList(SysSessionQueryDTO queryDTO, PageQuery pageQuery) {
        // 获取所有会话
        List<UserSession> allSessions = sessionHelper.getAllSessions();
        
        // 过滤
        List<SysSession> filteredList = allSessions.stream()
                .filter(session -> matchQuery(session, queryDTO))
                .map(this::convertToSysSession)
                .collect(Collectors.toList());
        
        // 手动分页
        int total = filteredList.size();
        int pageNum = pageQuery.getPageNum();
        int pageSize = pageQuery.getPageSize();
        int fromIndex = (pageNum - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, total);
        
        List<SysSession> pageList = fromIndex < total 
                ? filteredList.subList(fromIndex, toIndex) 
                : List.of();
        
        return PageResult.of(pageList, total, pageNum, pageSize);
    }

    /**
     * 匹配查询条件
     */
    private boolean matchQuery(UserSession session, SysSessionQueryDTO queryDTO) {
        if (queryDTO == null) return true;
        
        // 用户名模糊匹配
        if (StringUtils.isNotBlank(queryDTO.getUserName()) 
                && (session.getUserName() == null || !session.getUserName().contains(queryDTO.getUserName()))) {
            return false;
        }
        // 昵称模糊匹配
        if (StringUtils.isNotBlank(queryDTO.getNickName()) 
                && (session.getNickName() == null || !session.getNickName().contains(queryDTO.getNickName()))) {
            return false;
        }
        // 登录IP模糊匹配
        if (StringUtils.isNotBlank(queryDTO.getLoginIp()) 
                && (session.getLoginIp() == null || !session.getLoginIp().contains(queryDTO.getLoginIp()))) {
            return false;
        }
        // 平台类型精确匹配
        if (queryDTO.getPlatformType() != null 
                && (session.getPlatformType() == null || session.getPlatformType().getCode() != queryDTO.getPlatformType())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean kickOut(String sessionId) {
        return sessionHelper.kickOut(sessionId);
    }

    /**
     * 将 UserSession 转换为 SysSession
     */
    private SysSession convertToSysSession(UserSession userSession) {
        SysSession sysSession = new SysSession();
        sysSession.setSessionId(userSession.getSessionId());
        sysSession.setUserId(userSession.getUserId());
        sysSession.setUserName(userSession.getUserName());
        sysSession.setNickName(userSession.getNickName());
        sysSession.setPlatformType(userSession.getPlatformType());
        sysSession.setRoles(userSession.getRoles());
        sysSession.setPermissions(userSession.getPermissions());
        sysSession.setGroups(userSession.getGroups());
        sysSession.setDepts(userSession.getDepts());
        sysSession.setLoginIp(userSession.getLoginIp());
        sysSession.setLoginTime(userSession.getLoginTime());
        sysSession.setLastAccessTime(userSession.getLastAccessTime());
        sysSession.setAccessExpireTime(userSession.getAccessExpireTime());
        sysSession.setRefreshExpireTime(userSession.getRefreshExpireTime());
        return sysSession;
    }
}
