package com.junoyi.module.user.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.utils.poi.ExcelUtil;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.module.user.api.domain.entity.OrganizerInfo;
import com.junoyi.module.user.api.domain.vo.OrganizerInfoVO;
import com.junoyi.module.user.api.service.IOrganizerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user/organizer")
public class OrganizerController extends BaseController {

    @Autowired
    private IOrganizerInfoService organizerInfoService;

    @RequiresPermissions("user:organizer:list")
    @GetMapping("/list")
    public TableDataInfo list(OrganizerInfo organizerInfo) {
        startPage();
        List<OrganizerInfoVO> list = organizerInfoService.selectOrganizerInfoList(organizerInfo);
        return getDataTable(list);
    }

    @RequiresPermissions("user:organizer:export")
    @Log(title = "主办方信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrganizerInfo organizerInfo) {
        List<OrganizerInfoVO> list = organizerInfoService.selectOrganizerInfoList(organizerInfo);
        ExcelUtil<OrganizerInfoVO> util = new ExcelUtil<>(OrganizerInfoVO.class);
        util.exportExcel(response, list, "主办方信息数据");
    }

    @RequiresPermissions("user:organizer:query")
    @GetMapping(value = "/{id}")
    public R<OrganizerInfoVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(organizerInfoService.selectOrganizerInfoById(id));
    }

    @RequiresPermissions("user:organizer:add")
    @Log(title = "主办方信息", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@RequestBody OrganizerInfo organizerInfo) {
        return toAjax(organizerInfoService.insertOrganizerInfo(organizerInfo));
    }

    @RequiresPermissions("user:organizer:edit")
    @Log(title = "主办方信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@RequestBody OrganizerInfo organizerInfo) {
        return toAjax(organizerInfoService.updateOrganizerInfo(organizerInfo));
    }

    @RequiresPermissions("user:organizer:remove")
    @Log(title = "主办方信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(organizerInfoService.deleteOrganizerInfoByIds(ids));
    }
}
