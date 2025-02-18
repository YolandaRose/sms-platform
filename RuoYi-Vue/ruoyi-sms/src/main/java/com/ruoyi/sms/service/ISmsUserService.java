package com.ruoyi.sms.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.sms.domain.SmsUser;

public interface ISmsUserService {
    // 基础CRUD
    public SmsUser selectSmsUserById(Long id);
    public List<SmsUser> selectSmsUserList(SmsUser smsUser);
    public int insertSmsUser(SmsUser smsUser);
    public int updateSmsUser(SmsUser smsUser);
    public int deleteSmsUserById(Long id);
    public int deleteSmsUserByIds(Long[] ids);

    // 业务方法
    public SmsUser getUserByAccountAndApiKey(String account, String apiKey);
    public SmsUser getByAccessKey(String accessKey);
    public boolean deductBalance(Long userId, int amount);
    public Map<String, Object> getBalanceInfo(Long userId);
} 