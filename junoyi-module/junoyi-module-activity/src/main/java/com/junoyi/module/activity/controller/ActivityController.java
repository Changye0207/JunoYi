package com.junoyi.activity.controller;

import com.junoyi.framework.core.domain.module.R;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.framework.web.domain.BaseController;
import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import com.junoyi.module.activity.api.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动控制器
 * 提供活动相关的HTTP接口，包括活动列表查询、详情查询、可用性检查等功能
 * 继承自 BaseController，提供分页和统一响应格式支持
 */
@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private IActivityService activityService;

    @GetMapping("/list")
    public PageResult<ActivityVO> list(Activity activity) {
        startPage();
        List<ActivityVO> list = activityService.selectActivityList(activity);
        return toPageResult(list, list.size() * 1L);
    }

    @GetMapping(value = "/{id}")
    public R<ActivityVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(activityService.selectActivityById(id));
    }

    @GetMapping("/checkAvailable/{id}")
    public R<Boolean> checkAvailable(@PathVariable("id") Long id) {
        return R.ok(activityService.checkActivityAvailable(id));
    }
}
