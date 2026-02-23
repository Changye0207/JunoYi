package com.junoyi.module.growth.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.common.security.utils.SecurityUtils;
import com.junoyi.module.growth.api.domain.entity.GrowthRecord;
import com.junoyi.module.growth.api.domain.vo.GrowthRecordVO;
import com.junoyi.module.growth.api.service.IGrowthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/growth/record")
public class GrowthRecordController extends BaseController {

    @Autowired
    private IGrowthRecordService growthRecordService;

    @RequiresPermissions("growth:record:list")
    @GetMapping("/list")
    public TableDataInfo list(GrowthRecord growthRecord) {
        startPage();
        Long parentId = SecurityUtils.getUserId();
        growthRecord.setParentId(parentId);
        List<GrowthRecordVO> list = growthRecordService.selectGrowthRecordList(growthRecord);
        return getDataTable(list);
    }

    @RequiresPermissions("growth:record:query")
    @GetMapping(value = "/{id}")
    public R<GrowthRecordVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(growthRecordService.selectGrowthRecordById(id));
    }

    @RequiresPermissions("growth:record:add")
    @Log(title = "成长记录", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@RequestBody GrowthRecord growthRecord) {
        Long parentId = SecurityUtils.getUserId();
        growthRecord.setParentId(parentId);
        return toAjax(growthRecordService.insertGrowthRecord(growthRecord));
    }

    @RequiresPermissions("growth:record:edit")
    @Log(title = "成长记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@RequestBody GrowthRecord growthRecord) {
        return toAjax(growthRecordService.updateGrowthRecord(growthRecord));
    }

    @RequiresPermissions("growth:record:remove")
    @Log(title = "成长记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(growthRecordService.removeByIds(List.of(ids)) ? 1 : 0);
    }
}
