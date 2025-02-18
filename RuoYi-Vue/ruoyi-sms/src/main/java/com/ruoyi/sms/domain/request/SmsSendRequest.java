package com.ruoyi.sms.domain.request;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class SmsSendRequest {
    private String account;        // 账号
    private String apiKey;         // API密钥
    private String timestamp;      // 时间戳
    private String nonce;          // 随机数
    private String sign;           // 签名
    private String phoneNumbers;   // 手机号，多个用逗号分隔
    private String content;        // 短信内容
} 