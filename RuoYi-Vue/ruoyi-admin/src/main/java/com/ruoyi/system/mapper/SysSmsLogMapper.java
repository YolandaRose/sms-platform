package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysSmsLog;

/**
 * 短信发送日志Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
public interface SysSmsLogMapper 
{
    /**
     * 查询短信发送日志
     * 
     * @param id 短信发送日志主键
     * @return 短信发送日志
     */
    public SysSmsLog selectSysSmsLogById(Long id);

    /**
     * 查询短信发送日志列表
     * 
     * @param sysSmsLog 短信发送日志
     * @return 短信发送日志集合
     */
    public List<SysSmsLog> selectSysSmsLogList(SysSmsLog sysSmsLog);

    /**
     * 新增短信发送日志
     * 
     * @param sysSmsLog 短信发送日志
     * @return 结果
     */
    public int insertSysSmsLog(SysSmsLog sysSmsLog);

    /**
     * 修改短信发送日志
     * 
     * @param sysSmsLog 短信发送日志
     * @return 结果
     */
    public int updateSysSmsLog(SysSmsLog sysSmsLog);

    /**
     * 删除短信发送日志
     * 
     * @param id 短信发送日志主键
     * @return 结果
     */
    public int deleteSysSmsLogById(Long id);

    /**
     * 批量删除短信发送日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysSmsLogByIds(Long[] ids);
}
