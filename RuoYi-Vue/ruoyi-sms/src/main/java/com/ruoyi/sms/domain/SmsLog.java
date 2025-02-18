package com.ruoyi.sms.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

@Data
@TableName("sys_sms_log")
public class SmsLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 日志ID */
    @Excel(name = "日志编号")
    private Long id;
    
    /** 用户ID */
    private Long userId;
    
    /** 发送账号 */
    @Excel(name = "发送账号")
    @TableField(exist = false)
    private String account;
    
    /** 接收手机号 */
    @Excel(name = "接收手机号")
    private String recipient;
    
    /** 短信内容 */
    @Excel(name = "短信内容")
    private String content;
    
    /** 发送状态 */
    @Excel(name = "发送状态", readConverterExp = "0=失败,1=成功")
    private String status;
    
    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    
    /** 备注 */
    @Excel(name = "备注")
    private String remark;

} 