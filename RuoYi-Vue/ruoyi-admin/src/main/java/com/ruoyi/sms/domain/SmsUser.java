package com.ruoyi.sms.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 短信平台用户管理对象 sms_user
 *
 * @author ruoyi
 * @date 2025-02-13
 */
@Data
public class SmsUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** api密钥 */
    @Excel(name = "api密钥")
    private String apiKey;

    /** API安全密钥 */
    @Excel(name = "API安全密钥")
    private String secretKey;

    /** 短信余额 */
    @Excel(name = "短信余额")
    private Integer balance;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("apiKey", getApiKey())
            .append("balance", getBalance())
            .append("secretKey", getSecretKey())
            .toString();
    }
}
