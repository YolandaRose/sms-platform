package com.ruoyi.web.controller.sms;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.sms.domain.SmsLog;
import com.ruoyi.sms.service.ISmsLogService;

@RestController
@RequestMapping("/sms/log")
public class SmsLogController extends BaseController {
    
    @Autowired
    private ISmsLogService smsLogService;

    /**
     * 查询短信发送记录列表
     */
    @PreAuthorize("@ss.hasPermi('sms:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SmsLog smsLog) {
        startPage();
        List<SmsLog> list = smsLogService.selectSmsLogList(smsLog);
        return getDataTable(list);
    }

    /**
     * 获取短信发送记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('sms:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(smsLogService.selectSmsLogById(id));
    }
} 