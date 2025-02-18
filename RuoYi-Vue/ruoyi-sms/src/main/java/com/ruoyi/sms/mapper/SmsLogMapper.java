package com.ruoyi.sms.mapper;

import java.util.List;
import com.ruoyi.sms.domain.SmsLog;

public interface SmsLogMapper {
    /**
     * 查询短信发送记录列表
     */
    public List<SmsLog> selectSmsLogList(SmsLog smsLog);
    
    /**
     * 查询短信发送记录详细
     */
    public SmsLog selectSmsLogById(Long id);
    
    /**
     * 新增短信发送记录
     */
    public int insertSmsLog(SmsLog smsLog);
} 