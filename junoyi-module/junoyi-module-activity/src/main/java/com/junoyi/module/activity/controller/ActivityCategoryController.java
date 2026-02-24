package com.junoyi.activity.controller;

import com.junoyi.framework.core.domain.module.R;
import com.junoyi.framework.core.domain.page.PageResult;
import com.junoyi.framework.excel.utlis.ExcelUtils;
import com.junoyi.framework.web.domain.BaseController;
import com.junoyi.framework.permission.annotation.Permission;
import com.junoyi.activity.api.domain.entity.ActivityCategory;
import com.junoyi.activity.api.domain.vo.ActivityCategoryVO;
import com.junoyi.activity.api.service.IActivityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 活动分类控制器
 * 提供活动分类相关的HTTP接口，包括分类列表查询、添加、编辑、删除、导出等功能
 * 继承自 BaseController，提供分页和统一响应格式支持
 */
@RestController
@RequestMapping("/activity/category")
public class ActivityCategoryController extends BaseController {

    @Autowired
    private IActivityCategoryService activityCategoryService;

    /**
     * 获取活动分类列表
     * 根据查询条件分页查询活动分类列表，并返回分页结果
     * @param activityCategory 查询条件实体
     * @return 活动分类VO分页结果
     */
    @Permission("activity:category:list")
    @GetMapping("/list")
    public PageResult<ActivityCategoryVO> list(ActivityCategory activityCategory) {
        startPage();
        List<ActivityCategoryVO> list = activityCategoryService.selectActivityCategoryList(activityCategory);
        return toPageResult(list, list.size() * 1L);
    }

    /**
     * 导出活动分类数据
     * 将活动分类数据导出为Excel文件
     * @param response HTTP响应对象
     * @param activityCategory 查询条件实体
     */
    @Permission("activity:category:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActivityCategory activityCategory) {
        List<ActivityCategoryVO> list = activityCategoryService.selectActivityCategoryList(activityCategory);
        ExcelUtils.exportExcel(list, "活动分类数据", ActivityCategoryVO.class, response);
    }

    /**
     * 获取活动分类详细信息
     * 根据分类ID查询活动分类的详细信息
     * @param id 分类ID
     * @return 活动分类详细信息VO
     */
    @Permission("activity:category:query")
    @GetMapping(value = "/{id}")
    public R<ActivityCategoryVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(activityCategoryService.selectActivityCategoryById(id));
    }

    /**
     * 添加活动分类
     * 创建一个新的活动分类
     * @param activityCategory 活动分类实体
     * @return 操作结果
     */
    @Permission("activity:category:add")
    @PostMapping
    public R<Void> add(@RequestBody ActivityCategory activityCategory) {
        return toAjax(activityCategoryService.insertActivityCategory(activityCategory));
    }

    /**
     * 编辑活动分类
     * 更新活动分类的信息
     * @param activityCategory 活动分类实体
     * @return 操作结果
     */
    @Permission("activity:category:edit")
    @PutMapping
    public R<Void> edit(@RequestBody ActivityCategory activityCategory) {
        return toAjax(activityCategoryService.updateActivityCategory(activityCategory));
    }

    /**
     * 删除活动分类
     * 根据分类ID数组删除多个活动分类
     * @param ids 分类ID数组
     * @return 操作结果
     */
    @Permission("activity:category:remove")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(activityCategoryService.deleteActivityCategoryByIds(ids));
    }
}
