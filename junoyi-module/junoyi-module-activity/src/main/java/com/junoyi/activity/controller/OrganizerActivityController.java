package com.junoyi.module.activity.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.common.security.utils.SecurityUtils;
import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import com.junoyi.activity.api.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizer/activity")
public class OrganizerActivityController extends BaseController {

    @Autowired
    private IActivityService activityService;

    @RequiresPermissions("activity:activity:list")
    @GetMapping("/list")
    public TableDataInfo list(Activity activity) {
        startPage();
        Long organizerId = SecurityUtils.getUserId();
        List<ActivityVO> list = activityService.selectActivityListByOrganizerId(organizerId);
        return getDataTable(list);
    }

    @RequiresPermissions("activity:activity:query")
    @GetMapping(value = "/{id}")
    public R<ActivityVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(activityService.selectActivityById(id));
    }

    @RequiresPermissions("activity:activity:add")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@RequestBody Activity activity) {
        Long organizerId = SecurityUtils.getUserId();
        activity.setOrganizerId(organizerId);
        return toAjax(activityService.insertActivity(activity));
    }

    @RequiresPermissions("activity:activity:edit")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@RequestBody Activity activity) {
        return toAjax(activityService.updateActivity(activity));
    }

    @RequiresPermissions("activity:activity:publish")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping("/publish/{id}")
    public R<Void> publish(@PathVariable Long id) {
        return toAjax(activityService.publishActivity(id));
    }

    @RequiresPermissions("activity:activity:offline")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping("/offline/{id}")
    public R<Void> offline(@PathVariable Long id) {
        return toAjax(activityService.offlineActivity(id));
    }
}
