package com.ruoyi.sms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sms.mapper.SmsLogMapper;
import com.ruoyi.sms.domain.SmsLog;
import com.ruoyi.sms.service.ISmsLogService;

@Service
public class SmsLogServiceImpl implements ISmsLogService {
    @Autowired
    private SmsLogMapper smsLogMapper;

    @Override
    public List<SmsLog> selectSmsLogList(SmsLog smsLog) {
        return smsLogMapper.selectSmsLogList(smsLog);
    }

    @Override
    public SmsLog selectSmsLogById(Long id) {
        return smsLogMapper.selectSmsLogById(id);
    }

    /**
     * 插入短信日志
     */
    @Override
    public int insertSmsLog(SmsLog smsLog) {
        return smsLogMapper.insertSmsLog(smsLog);
    }
} 