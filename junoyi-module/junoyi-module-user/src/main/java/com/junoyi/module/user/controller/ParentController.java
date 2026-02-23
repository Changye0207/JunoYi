package com.junoyi.module.user.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.utils.poi.ExcelUtil;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.module.user.api.domain.entity.ParentInfo;
import com.junoyi.module.user.api.domain.vo.ParentInfoVO;
import com.junoyi.module.user.api.service.IParentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user/parent")
public class ParentController extends BaseController {

    @Autowired
    private IParentInfoService parentInfoService;

    @RequiresPermissions("user:parent:list")
    @GetMapping("/list")
    public TableDataInfo list(ParentInfo parentInfo) {
        startPage();
        List<ParentInfoVO> list = parentInfoService.selectParentInfoList(parentInfo);
        return getDataTable(list);
    }

    @RequiresPermissions("user:parent:export")
    @Log(title = "家长信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ParentInfo parentInfo) {
        List<ParentInfoVO> list = parentInfoService.selectParentInfoList(parentInfo);
        ExcelUtil<ParentInfoVO> util = new ExcelUtil<>(ParentInfoVO.class);
        util.exportExcel(response, list, "家长信息数据");
    }

    @RequiresPermissions("user:parent:query")
    @GetMapping(value = "/{id}")
    public R<ParentInfoVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(parentInfoService.selectParentInfoById(id));
    }

    @RequiresPermissions("user:parent:add")
    @Log(title = "家长信息", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@RequestBody ParentInfo parentInfo) {
        return toAjax(parentInfoService.insertParentInfo(parentInfo));
    }

    @RequiresPermissions("user:parent:edit")
    @Log(title = "家长信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@RequestBody ParentInfo parentInfo) {
        return toAjax(parentInfoService.updateParentInfo(parentInfo));
    }

    @RequiresPermissions("user:parent:remove")
    @Log(title = "家长信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(parentInfoService.deleteParentInfoByIds(ids));
    }
}
