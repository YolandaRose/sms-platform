package com.ruoyi.sms.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sms.mapper.SmsUserMapper;
import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.service.ISmsUserService;

/**
 * 短信平台用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@Service
public class SmsUserServiceImpl implements ISmsUserService 
{
    @Autowired
    private SmsUserMapper smsUserMapper;

    /**
     * 查询短信平台用户管理
     * 
     * @param id 短信平台用户管理主键
     * @return 短信平台用户管理
     */
    @Override
    public SmsUser selectSmsUserById(Long id)
    {
        return smsUserMapper.selectSmsUserById(id);
    }

    /**
     * 查询短信平台用户管理列表
     * 
     * @param smsUser 短信平台用户管理
     * @return 短信平台用户管理
     */
    @Override
    public List<SmsUser> selectSmsUserList(SmsUser smsUser)
    {
        return smsUserMapper.selectSmsUserList(smsUser);
    }

    /**
     * 新增短信平台用户管理
     * 
     * @param smsUser 短信平台用户管理
     * @return 结果
     */
    @Override
    public int insertSmsUser(SmsUser smsUser)
    {
        return smsUserMapper.insertSmsUser(smsUser);
    }

    /**
     * 修改短信平台用户管理
     * 
     * @param smsUser 短信平台用户管理
     * @return 结果
     */
    @Override
    public int updateSmsUser(SmsUser smsUser)
    {
        return smsUserMapper.updateSmsUser(smsUser);
    }

    /**
     * 批量删除短信平台用户管理
     * 
     * @param ids 需要删除的短信平台用户管理主键
     * @return 结果
     */
    @Override
    public int deleteSmsUserByIds(Long[] ids)
    {
        return smsUserMapper.deleteSmsUserByIds(ids);
    }

    /**
     * 删除短信平台用户管理信息
     * 
     * @param id 短信平台用户管理主键
     * @return 结果
     */
    @Override
    public int deleteSmsUserById(Long id)
    {
        return smsUserMapper.deleteSmsUserById(id);
    }

    @Override
    public SmsUser getByAccessKey(String accessKey) {
        return smsUserMapper.getByAccessKey(accessKey);
    }

    @Override
    public int deductBalance(Long userId, int amount) {
        return smsUserMapper.deductBalance(userId, amount);
    }
}
