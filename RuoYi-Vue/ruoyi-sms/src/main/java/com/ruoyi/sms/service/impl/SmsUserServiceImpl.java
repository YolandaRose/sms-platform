package com.ruoyi.sms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.mapper.SmsUserMapper;
import com.ruoyi.sms.service.ISmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SmsUserServiceImpl implements ISmsUserService {

    private static final Logger log = LoggerFactory.getLogger(SmsUserServiceImpl.class);

    @Autowired
    private SmsUserMapper smsUserMapper;

    @Override
    public SmsUser getUserByAccountAndApiKey(String account, String apiKey) {
        SmsUser user = new SmsUser();
        user.setAccount(account);
        user.setApiKey(apiKey);
        List<SmsUser> list = smsUserMapper.selectSmsUserList(user);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public SmsUser getByAccessKey(String accessKey) {
        return smsUserMapper.selectByAccessKey(accessKey);
    }

    @Override
    @Transactional
    public boolean deductBalance(Long userId, int amount) {
        // 先查询当前余额
        SmsUser user = smsUserMapper.selectSmsUserById(userId);
        log.info("用户 {} 当前余额: {}, 需要扣减: {}", userId, user != null ? user.getBalance() : "用户不存在", amount);
        
        // 执行扣减
        int result = smsUserMapper.deductBalance(userId, amount);
        log.info("扣减结果: {}", result);
        
        return result > 0;
    }

    @Override
    public Map<String, Object> getBalanceInfo(Long userId) {
        SmsUser user = smsUserMapper.selectSmsUserById(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("balance", user != null ? user.getBalance() : 0);
        return result;
    }

    @Override
    public SmsUser selectSmsUserById(Long id) {
        return smsUserMapper.selectSmsUserById(id);
    }

    @Override
    public List<SmsUser> selectSmsUserList(SmsUser smsUser) {
        return smsUserMapper.selectSmsUserList(smsUser);
    }

    @Override
    public int insertSmsUser(SmsUser smsUser) {
        log.info("准备插入数据库的用户数据: {}", smsUser);
        int rows = smsUserMapper.insertSmsUser(smsUser);
        log.info("插入结果: {}", rows);
        return rows;
    }

    @Override
    public int updateSmsUser(SmsUser smsUser) {
        return smsUserMapper.updateSmsUser(smsUser);
    }

    @Override
    public int deleteSmsUserById(Long id) {
        return smsUserMapper.deleteSmsUserById(id);
    }

    @Override
    public int deleteSmsUserByIds(Long[] ids) {
        return smsUserMapper.deleteSmsUserByIds(ids);
    }
} 