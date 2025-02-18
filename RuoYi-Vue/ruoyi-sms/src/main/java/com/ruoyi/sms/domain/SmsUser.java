package com.ruoyi.sms.domain;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_sms_user")
public class SmsUser extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    /** 用户ID */
    private Long id;
    
    /** 账号 */
    private String account;
    
    /** 密码 */
    private String password;
    
    /** API密钥 */
    private String apiKey;
    
    /** 安全密钥 */
    private String secretKey;
    
    /** 余额 */
    private Integer balance;
} 