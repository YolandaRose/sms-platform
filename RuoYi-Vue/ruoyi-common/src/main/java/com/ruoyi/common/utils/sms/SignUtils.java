package com.ruoyi.common.utils.sms;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class SignUtils {
    
    /**
     * 生成签名
     */
    public static String generateSign(String apiKey, String timestamp, String nonce, String secretKey) {
        // 1. 按照固定顺序拼接参数
        String signStr = apiKey + timestamp + nonce + secretKey;
        
        // 2. 计算 MD5 并转大写
        return DigestUtils.md5Hex(signStr).toUpperCase();
    }
    
    /**
     * 验证签名
     */
    public static boolean validateSign(String apiKey, String timestamp, String nonce, String sign, String secretKey) {
        // 参数校验
        if (!StringUtils.hasText(apiKey) || !StringUtils.hasText(timestamp) 
            || !StringUtils.hasText(nonce) || !StringUtils.hasText(sign)) {
            return false;
        }
        
        // 验证时间戳是否在5分钟内
        long now = System.currentTimeMillis();
        long requestTime = Long.parseLong(timestamp);
        if (Math.abs(now - requestTime) > 5 * 60 * 1000) {
            return false;
        }
        
        // 生成服务端签名并比较
        String serverSign = generateSign(apiKey, timestamp, nonce, secretKey);
        return serverSign.equals(sign);
    }
} 