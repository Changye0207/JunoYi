package com.junoyi.module.activity.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.utils.poi.ExcelUtil;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.activity.api.domain.entity.ActivityCategory;
import com.junoyi.activity.api.domain.vo.ActivityCategoryVO;
import com.junoyi.activity.api.service.IActivityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/activity/category")
public class ActivityCategoryController extends BaseController {

    @Autowired
    private IActivityCategoryService activityCategoryService;

    @RequiresPermissions("activity:category:list")
    @GetMapping("/list")
    public TableDataInfo list(ActivityCategory activityCategory) {
        startPage();
        List<ActivityCategoryVO> list = activityCategoryService.selectActivityCategoryList(activityCategory);
        return getDataTable(list);
    }

    @RequiresPermissions("activity:category:export")
    @Log(title = "活动分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityCategory activityCategory) {
        List<ActivityCategoryVO> list = activityCategoryService.selectActivityCategoryList(activityCategory);
        ExcelUtil<ActivityCategoryVO> util = new ExcelUtil<>(ActivityCategoryVO.class);
        util.exportExcel(response, list, "活动分类数据");
    }

    @RequiresPermissions("activity:category:query")
    @GetMapping(value = "/{id}")
    public R<ActivityCategoryVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(activityCategoryService.selectActivityCategoryById(id));
    }

    @RequiresPermissions("activity:category:add")
    @Log(title = "活动分类", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@RequestBody ActivityCategory activityCategory) {
        return toAjax(activityCategoryService.insertActivityCategory(activityCategory));
    }

    @RequiresPermissions("activity:category:edit")
    @Log(title = "活动分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@RequestBody ActivityCategory activityCategory) {
        return toAjax(activityCategoryService.updateActivityCategory(activityCategory));
    }

    @RequiresPermissions("activity:category:remove")
    @Log(title = "活动分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(activityCategoryService.deleteActivityCategoryByIds(ids));
    }
}
