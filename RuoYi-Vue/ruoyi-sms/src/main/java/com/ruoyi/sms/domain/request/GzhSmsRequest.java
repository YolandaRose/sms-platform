package com.ruoyi.sms.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GzhSmsRequest {
    private String account;   // 账号
    private String password;  // 密码
    private String phone;     // 手机号
    private String content;   // 短信内容
} 