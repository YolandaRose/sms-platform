package com.ruoyi.sms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruoyi.sms.domain.SmsUser;

@Mapper
public interface SmsUserMapper {
    
    public List<SmsUser> selectSmsUserList(SmsUser user);
    
    public SmsUser selectSmsUserById(Long id);
    
    public int insertSmsUser(SmsUser user);
    
    public int updateSmsUser(SmsUser user);
    
    public int deleteSmsUserById(Long id);
    
    public int deleteSmsUserByIds(Long[] ids);
    
    public SmsUser selectByAccessKey(String accessKey);
    
    /**
     * 扣减余额
     */
    public int deductBalance(@Param("userId") Long userId, @Param("amount") int amount);
} 