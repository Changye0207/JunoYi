package com.junoyi.module.activity.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.framework.web.controller.BaseController;
import com.junoyi.activity.api.domain.entity.Activity;
import com.junoyi.activity.api.domain.vo.ActivityVO;
import com.junoyi.activity.api.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private IActivityService activityService;

    @GetMapping("/list")
    public TableDataInfo list(Activity activity) {
        startPage();
        List<ActivityVO> list = activityService.selectActivityList(activity);
        return getDataTable(list);
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
