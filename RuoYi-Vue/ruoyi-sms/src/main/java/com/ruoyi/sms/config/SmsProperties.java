package com.ruoyi.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {
    private boolean mock;
    private GzhConfig gzh;
    
    @Data
    public static class GzhConfig {
        private String apiUrl;
        private String account;
        private String password;
        private String sign;
    }
} 