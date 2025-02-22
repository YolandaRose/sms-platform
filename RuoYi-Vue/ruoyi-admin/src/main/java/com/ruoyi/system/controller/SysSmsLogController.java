package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.SysSmsLog;
import com.ruoyi.system.service.ISysSmsLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 短信发送日志Controller
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
@RestController
@RequestMapping("/system/log")
public class SysSmsLogController extends BaseController
{
    @Autowired
    private ISysSmsLogService sysSmsLogService;

    /**
     * 查询短信发送日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysSmsLog sysSmsLog)
    {
        startPage();
        List<SysSmsLog> list = sysSmsLogService.selectSysSmsLogList(sysSmsLog);
        return getDataTable(list);
    }

    /**
     * 导出短信发送日志列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "短信发送日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysSmsLog sysSmsLog)
    {
        List<SysSmsLog> list = sysSmsLogService.selectSysSmsLogList(sysSmsLog);
        ExcelUtil<SysSmsLog> util = new ExcelUtil<SysSmsLog>(SysSmsLog.class);
        util.exportExcel(response, list, "短信发送日志数据");
    }

    /**
     * 获取短信发送日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysSmsLogService.selectSysSmsLogById(id));
    }

    /**
     * 新增短信发送日志
     */
    @PreAuthorize("@ss.hasPermi('system:log:add')")
    @Log(title = "短信发送日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysSmsLog sysSmsLog)
    {
        return toAjax(sysSmsLogService.insertSysSmsLog(sysSmsLog));
    }

    /**
     * 修改短信发送日志
     */
    @PreAuthorize("@ss.hasPermi('system:log:edit')")
    @Log(title = "短信发送日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysSmsLog sysSmsLog)
    {
        return toAjax(sysSmsLogService.updateSysSmsLog(sysSmsLog));
    }

    /**
     * 删除短信发送日志
     */
    @PreAuthorize("@ss.hasPermi('system:log:remove')")
    @Log(title = "短信发送日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysSmsLogService.deleteSysSmsLogByIds(ids));
    }
}
