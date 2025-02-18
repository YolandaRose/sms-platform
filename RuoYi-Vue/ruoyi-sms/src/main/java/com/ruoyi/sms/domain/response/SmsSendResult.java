package com.ruoyi.sms.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsSendResult {
    private String messageId;    // 消息ID
    private Integer fee;         // 计费条数
    private String status;       // 发送状态
    private String message;      // 状态说明
} 