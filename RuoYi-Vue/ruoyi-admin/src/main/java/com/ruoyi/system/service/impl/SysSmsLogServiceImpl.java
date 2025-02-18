package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysSmsLogMapper;
import com.ruoyi.system.domain.SysSmsLog;
import com.ruoyi.system.service.ISysSmsLogService;

/**
 * 短信发送日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
@Service
public class SysSmsLogServiceImpl implements ISysSmsLogService 
{
    @Autowired
    private SysSmsLogMapper sysSmsLogMapper;

    /**
     * 查询短信发送日志
     * 
     * @param id 短信发送日志主键
     * @return 短信发送日志
     */
    @Override
    public SysSmsLog selectSysSmsLogById(Long id)
    {
        return sysSmsLogMapper.selectSysSmsLogById(id);
    }

    /**
     * 查询短信发送日志列表
     * 
     * @param sysSmsLog 短信发送日志
     * @return 短信发送日志
     */
    @Override
    public List<SysSmsLog> selectSysSmsLogList(SysSmsLog sysSmsLog)
    {
        return sysSmsLogMapper.selectSysSmsLogList(sysSmsLog);
    }

    /**
     * 新增短信发送日志
     * 
     * @param sysSmsLog 短信发送日志
     * @return 结果
     */
    @Override
    public int insertSysSmsLog(SysSmsLog sysSmsLog)
    {
        return sysSmsLogMapper.insertSysSmsLog(sysSmsLog);
    }

    /**
     * 修改短信发送日志
     * 
     * @param sysSmsLog 短信发送日志
     * @return 结果
     */
    @Override
    public int updateSysSmsLog(SysSmsLog sysSmsLog)
    {
        return sysSmsLogMapper.updateSysSmsLog(sysSmsLog);
    }

    /**
     * 批量删除短信发送日志
     * 
     * @param ids 需要删除的短信发送日志主键
     * @return 结果
     */
    @Override
    public int deleteSysSmsLogByIds(Long[] ids)
    {
        return sysSmsLogMapper.deleteSysSmsLogByIds(ids);
    }

    /**
     * 删除短信发送日志信息
     * 
     * @param id 短信发送日志主键
     * @return 结果
     */
    @Override
    public int deleteSysSmsLogById(Long id)
    {
        return sysSmsLogMapper.deleteSysSmsLogById(id);
    }
}
