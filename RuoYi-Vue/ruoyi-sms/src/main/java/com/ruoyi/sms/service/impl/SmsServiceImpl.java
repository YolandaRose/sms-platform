package com.ruoyi.sms.service.impl;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ruoyi.sms.domain.SmsLog;
import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.domain.request.GzhSmsRequest;
import com.ruoyi.sms.domain.request.SmsSendRequest;
import com.ruoyi.sms.domain.response.GzhSmsResponse;
import com.ruoyi.sms.domain.response.SmsSendResult;
import com.ruoyi.sms.config.GzhSmsConfig;
import com.ruoyi.sms.config.SmsProperties;
import com.ruoyi.sms.mapper.SmsLogMapper;
import com.ruoyi.sms.service.ISmsService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsServiceImpl implements ISmsService {
    
    @Autowired
    private GzhSmsConfig smsConfig;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private SmsLogMapper smsLogMapper;
    
    @Autowired
    private SmsProperties smsProperties;
    
    @Override
    public SmsSendResult send(SmsSendRequest request, SmsUser smsUser) {
        String messageId = UUID.randomUUID().toString();
        
        if (smsProperties.isMock()) {
            // 模拟发送
            log.info("模拟发送短信 -> 手机号: {}, 内容: {}", 
                request.getPhoneNumbers(), 
                request.getContent());
                
            return SmsSendResult.builder()
                    .messageId(messageId)
                    .fee(1)
                    .status("success")
                    .message("模拟发送成功")
                    .build();
        }
        
        // 2. 计算费用(按照号码数计费)
        String[] phones = request.getPhoneNumbers().split(",");
        int fee = phones.length;
        
        // 3. 发送短信
        boolean success = true;
        String message = "发送成功";
        try {
            for (String phone : phones) {
                // 替换模板参数
                String content = request.getContent();
                success = sendToGzhPort(phone, content);
                if (!success) {
                    message = "部分号码发送失败";
                }
            }
        } catch (Exception e) {
            log.error("发送短信异常", e);
            success = false;
            message = "发送异常：" + e.getMessage();
        }
        
        // 4. 构建结果
        return SmsSendResult.builder()
                .messageId(messageId)
                .fee(fee)
                .status(success ? "success" : "fail")
                .message(message)
                .build();
    }

    @Override
    public void saveLog(SmsSendRequest request, SmsSendResult result, SmsUser smsUser) {
        SmsLog smsLog = new SmsLog();
        smsLog.setUserId(smsUser.getId());
        smsLog.setRecipient(request.getPhoneNumbers());
        smsLog.setContent(request.getContent());
        smsLog.setStatus(result.getStatus());
        smsLog.setSendTime(new Date());
        smsLogMapper.insertSmsLog(smsLog);
    }

    @Override
    public void logSmsRecord(Long userId, String recipient, String message) {
        SmsLog smsLog = new SmsLog();
        smsLog.setUserId(userId);
        smsLog.setRecipient(recipient);
        smsLog.setContent(message);
        smsLog.setSendTime(new Date());
        smsLog.setStatus("1"); // 发送成功
        smsLogMapper.insertSmsLog(smsLog);
    }

    private boolean sendToGzhPort(String recipient, String content) {
        try {
            GzhSmsRequest request = GzhSmsRequest.builder()
                    .account(smsConfig.getAccount())
                    .password(smsConfig.getPassword())
                    .phone(recipient)
                    .content(smsConfig.getSign() + content)
                    .build();
            
            GzhSmsResponse response = restTemplate.postForObject(
                    smsConfig.getApiUrl(), 
                    request, 
                    GzhSmsResponse.class);
                    
            return response != null && "0000".equals(response.getCode());
        } catch (Exception e) {
            log.error("调用短信平台异常", e);
            return false;
        }
    }
} 