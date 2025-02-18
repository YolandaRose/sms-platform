package com.ruoyi.sms.service;

import com.ruoyi.sms.domain.request.SmsSendRequest;
import com.ruoyi.sms.domain.response.SmsSendResult;
import com.ruoyi.sms.domain.SmsUser;

public interface ISmsService {
    /**
     * 发送短信
     */
    SmsSendResult send(SmsSendRequest request, SmsUser smsUser);
    
    /**
     * 保存发送日志
     */
    void saveLog(SmsSendRequest request, SmsSendResult result, SmsUser smsUser);
    
    /**
     * 记录短信发送日志
     */
    void logSmsRecord(Long userId, String recipient, String message);
} 