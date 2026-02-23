package com.junoyi.module.appointment.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.common.security.utils.SecurityUtils;
import com.junoyi.module.appointment.api.domain.entity.Appointment;
import com.junoyi.module.appointment.api.domain.vo.AppointmentVO;
import com.junoyi.module.appointment.api.service.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentController extends BaseController {

    @Autowired
    private IAppointmentService appointmentService;

    @RequiresPermissions("appointment:appointment:list")
    @GetMapping("/list")
    public TableDataInfo list(Appointment appointment) {
        startPage();
        Long parentId = SecurityUtils.getUserId();
        appointment.setParentId(parentId);
        List<AppointmentVO> list = appointmentService.selectAppointmentList(appointment);
        return getDataTable(list);
    }

    @RequiresPermissions("appointment:appointment:query")
    @GetMapping(value = "/{id}")
    public R<AppointmentVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(appointmentService.selectAppointmentById(id));
    }

    @RequiresPermissions("appointment:appointment:add")
    @Log(title = "预约管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Appointment> add(@RequestBody Map<String, Object> params) {
        Appointment appointment = new Appointment();
        appointment.setActivityId(Long.valueOf(params.get("activityId").toString()));
        appointment.setParentId(SecurityUtils.getUserId());

        @SuppressWarnings("unchecked")
        List<Long> studentIds = (List<Long>) params.get("studentIds");

        Appointment result = appointmentService.createAppointment(appointment, studentIds);
        return R.ok(result);
    }

    @RequiresPermissions("appointment:appointment:edit")
    @Log(title = "预约管理", businessType = BusinessType.UPDATE)
    @PutMapping("/cancel/{id}")
    public R<Void> cancel(@PathVariable Long id, @RequestBody Map<String, String> params) {
        String reason = params.getOrDefault("reason", "用户主动取消");
        return toAjax(appointmentService.cancelAppointment(id, reason));
    }
}
