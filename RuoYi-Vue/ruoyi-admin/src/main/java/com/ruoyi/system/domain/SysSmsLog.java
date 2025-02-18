package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 短信发送日志对象 sys_sms_log
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
public class SysSmsLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 接收手机号 */
    @Excel(name = "接收手机号")
    private String recipient;

    /** 短信内容 */
    @Excel(name = "短信内容")
    private String content;

    /** 发送状态（0失败 1成功） */
    @Excel(name = "发送状态", readConverterExp = "0=失败,1=成功")
    private String status;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRecipient(String recipient) 
    {
        this.recipient = recipient;
    }

    public String getRecipient() 
    {
        return recipient;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("recipient", getRecipient())
            .append("content", getContent())
            .append("status", getStatus())
            .append("sendTime", getSendTime())
            .append("remark", getRemark())
            .toString();
    }
}
