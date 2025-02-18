package com.ruoyi.sms.service;

import java.util.List;
import com.ruoyi.sms.domain.SmsLog;

public interface ISmsLogService {
    /**
     * 查询短信发送记录列表
     */
    List<SmsLog> selectSmsLogList(SmsLog smsLog);

    /**
     * 查询短信发送记录详细
     */
    SmsLog selectSmsLogById(Long id);

    /**
     * 插入短信日志
     */
    int insertSmsLog(SmsLog smsLog);
} 