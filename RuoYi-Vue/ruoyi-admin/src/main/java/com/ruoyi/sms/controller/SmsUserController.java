package com.ruoyi.sms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.service.ISmsUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信平台用户管理Controller
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@RestController
@RequestMapping("/sms/smsuser")
public class SmsUserController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(SmsUserController.class);

    @Autowired
    private ISmsUserService smsUserService;

    /**
     * 查询短信平台用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('sms:smsuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(SmsUser smsUser)
    {
        startPage();
        List<SmsUser> list = smsUserService.selectSmsUserList(smsUser);
        return getDataTable(list);
    }

    /**
     * 导出短信平台用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('sms:smsuser:export')")
    @Log(title = "短信平台用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SmsUser smsUser)
    {
        List<SmsUser> list = smsUserService.selectSmsUserList(smsUser);
        ExcelUtil<SmsUser> util = new ExcelUtil<SmsUser>(SmsUser.class);
        util.exportExcel(response, list, "短信平台用户管理数据");
    }

    /**
     * 获取短信平台用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('sms:smsuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(smsUserService.selectSmsUserById(id));
    }

    /**
     * 新增短信平台用户管理
     */
    @PreAuthorize("@ss.hasPermi('sms:smsuser:add')")
    @Log(title = "短信平台用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SmsUser smsUser)
    {
        log.info("接收到的新增用户数据: {}", smsUser);
        if (smsUser.getSecretKey() == null) {
            log.warn("API安全密钥为空");
            return error("API安全密钥不能为空");
        }
        return toAjax(smsUserService.insertSmsUser(smsUser));
    }

    /**
     * 修改短信平台用户管理
     */
    @PreAuthorize("@ss.hasPermi('sms:smsuser:edit')")
    @Log(title = "短信平台用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SmsUser smsUser)
    {
        return toAjax(smsUserService.updateSmsUser(smsUser));
    }

    /**
     * 删除短信平台用户管理
     */
    @PreAuthorize("@ss.hasPermi('sms:smsuser:remove')")
    @Log(title = "短信平台用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(smsUserService.deleteSmsUserByIds(ids));
    }
}
