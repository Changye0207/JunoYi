package com.junoyi.activity.controller;

import com.junoyi.framework.core.domain.module.R;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.framework.web.domain.BaseController;
import com.junoyi.framework.permission.annotation.Permission;
import com.junoyi.framework.security.utils.SecurityUtils;
import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import com.junoyi.module.activity.api.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizer/activity")
public class OrganizerActivityController extends BaseController {

    @Autowired
    private IActivityService activityService;

    @Permission("activity:activity:list")
    @GetMapping("/list")
    public PageResult<ActivityVO> list(Activity activity) {
        startPage();
        Long organizerId = SecurityUtils.getUserId();
        List<ActivityVO> list = activityService.selectActivityListByOrganizerId(organizerId);
        return toPageResult(list, list.size() * 1L);
    }

    @Permission("activity:activity:query")
    @GetMapping(value = "/{id}")
    public R<ActivityVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(activityService.selectActivityById(id));
    }

    @Permission("activity:activity:add")
    @PostMapping
    public R<Void> add(@RequestBody Activity activity) {
        Long organizerId = SecurityUtils.getUserId();
        activity.setOrganizerId(organizerId);
        return toAjax(activityService.insertActivity(activity));
    }

    @Permission("activity:activity:edit")
    @PutMapping
    public R<Void> edit(@RequestBody Activity activity) {
        return toAjax(activityService.updateActivity(activity));
    }

    @Permission("activity:activity:publish")
    @PutMapping("/publish/{id}")
    public R<Void> publish(@PathVariable Long id) {
        return toAjax(activityService.publishActivity(id));
    }

    @Permission("activity:activity:offline")
    @PutMapping("/offline/{id}")
    public R<Void> offline(@PathVariable Long id) {
        return toAjax(activityService.offlineActivity(id));
    }
}
