package com.junoyi.module.payment.controller;

import com.junoyi.common.core.domain.R;
import com.junoyi.common.core.page.TableDataInfo;
import com.junoyi.common.core.web.controller.BaseController;
import com.junoyi.common.log.annotation.Log;
import com.junoyi.common.log.enums.BusinessType;
import com.junoyi.common.security.annotation.RequiresPermissions;
import com.junoyi.common.security.utils.SecurityUtils;
import com.junoyi.module.payment.api.domain.entity.PaymentRecord;
import com.junoyi.module.payment.api.domain.vo.PaymentRecordVO;
import com.junoyi.module.payment.api.service.IPaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController {

    @Autowired
    private IPaymentRecordService paymentRecordService;

    @RequiresPermissions("payment:record:list")
    @GetMapping("/list")
    public TableDataInfo list(PaymentRecord paymentRecord) {
        startPage();
        Long parentId = SecurityUtils.getUserId();
        paymentRecord.setParentId(parentId);
        List<PaymentRecordVO> list = paymentRecordService.selectPaymentRecordList(paymentRecord);
        return getDataTable(list);
    }

    @RequiresPermissions("payment:record:query")
    @GetMapping(value = "/{id}")
    public R<PaymentRecordVO> getInfo(@PathVariable("id") Long id) {
        return R.ok(paymentRecordService.selectPaymentRecordById(id));
    }

    @RequiresPermissions("payment:record:add")
    @Log(title = "支付记录", businessType = BusinessType.INSERT)
    @PostMapping
    public R<PaymentRecord> add(@RequestBody PaymentRecord paymentRecord) {
        Long parentId = SecurityUtils.getUserId();
        paymentRecord.setParentId(parentId);
        return R.ok(paymentRecordService.createPayment(paymentRecord));
    }

    @RequiresPermissions("payment:record:edit")
    @Log(title = "支付记录", businessType = BusinessType.UPDATE)
    @PutMapping("/complete/{id}")
    public R<Void> complete(@PathVariable Long id, @RequestBody Map<String, String> params) {
        String transactionId = params.getOrDefault("transactionId", "");
        return toAjax(paymentRecordService.completePayment(id, transactionId));
    }

    @RequiresPermissions("payment:record:edit")
    @Log(title = "支付记录", businessType = BusinessType.UPDATE)
    @PutMapping("/refund/{id}")
    public R<Void> refund(@PathVariable Long id, @RequestBody Map<String, String> params) {
        String reason = params.getOrDefault("reason", "用户申请退款");
        return toAjax(paymentRecordService.refundPayment(id, reason));
    }
}
