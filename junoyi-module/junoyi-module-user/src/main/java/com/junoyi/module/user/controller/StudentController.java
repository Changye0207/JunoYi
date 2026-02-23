package com.junoyi.module.user.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.utils.poi.ExcelUtil;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.module.user.api.domain.entity.StudentInfo;
import com.junoyi.module.user.api.domain.vo.StudentInfoVO;
import com.junoyi.module.user.api.service.IStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user/student")
public class StudentController extends BaseController {

    @Autowired
    private IStudentInfoService studentInfoService;

    @RequiresPermissions("user:student:list")
    @GetMapping("/list")
    public TableDataInfo list(StudentInfo studentInfo) {
        startPage();
        List<StudentInfoVO> list = studentInfoService.selectStudentInfoList(studentInfo);
        return getDataTable(list);
    }

    @RequiresPermissions("user:student:export")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StudentInfo studentInfo) {
        List<StudentInfoVO> list = studentInfoService.selectStudentInfoList(studentInfo);
        ExcelUtil<StudentInfoVO> util = new ExcelUtil<>(StudentInfoVO.class);
        util.exportExcel(response, list, "学生信息数据");
    }

    @RequiresPermissions("user:student:query")
    @GetMapping(value = "/{id}")
    public R<StudentInfoVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(studentInfoService.selectStudentInfoById(id));
    }

    @RequiresPermissions("user:student:add")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@RequestBody StudentInfo studentInfo) {
        return toAjax(studentInfoService.insertStudentInfo(studentInfo));
    }

    @RequiresPermissions("user:student:edit")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@RequestBody StudentInfo studentInfo) {
        return toAjax(studentInfoService.updateStudentInfo(studentInfo));
    }

    @RequiresPermissions("user:student:remove")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(studentInfoService.deleteStudentInfoByIds(ids));
    }
}
