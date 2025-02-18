package com.ruoyi.sms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ruoyi.sms.domain.SmsUser;

/**
 * 短信平台用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@Mapper
public interface SmsUserMapper 
{
    /**
     * 查询短信平台用户管理
     * 
     * @param id 短信平台用户管理主键
     * @return 短信平台用户管理
     */
    public SmsUser selectSmsUserById(Long id);

    /**
     * 查询短信平台用户管理列表
     * 
     * @param smsUser 短信平台用户管理
     * @return 短信平台用户管理集合
     */
    public List<SmsUser> selectSmsUserList(SmsUser smsUser);

    /**
     * 新增短信平台用户管理
     * 
     * @param smsUser 短信平台用户管理
     * @return 结果
     */
    public int insertSmsUser(SmsUser smsUser);

    /**
     * 修改短信平台用户管理
     * 
     * @param smsUser 短信平台用户管理
     * @return 结果
     */
    public int updateSmsUser(SmsUser smsUser);

    /**
     * 删除短信平台用户管理
     * 
     * @param id 短信平台用户管理主键
     * @return 结果
     */
    public int deleteSmsUserById(Long id);

    /**
     * 批量删除短信平台用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSmsUserByIds(Long[] ids);

    /**
     * 根据accessKey查询用户
     * 
     * @param accessKey 访问密钥
     * @return 用户信息
     */
    public SmsUser getByAccessKey(String accessKey);

    /**
     * 扣减余额
     */
    public int deductBalance(@Param("userId") Long userId, @Param("amount") int amount);
}
