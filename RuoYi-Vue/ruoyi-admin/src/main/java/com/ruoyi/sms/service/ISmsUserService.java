package com.ruoyi.sms.service;

import java.util.List;

import com.ruoyi.sms.domain.SmsUser;

/**
 * 短信平台用户管理Service接口
 *
 * @author ruoyi
 * @date 2025-02-13
 */
public interface ISmsUserService
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
     * 批量删除短信平台用户管理
     *
     * @param ids 需要删除的短信平台用户管理主键集合
     * @return 结果
     */
    public int deleteSmsUserByIds(Long[] ids);

    /**
     * 删除短信平台用户管理信息
     *
     * @param id 短信平台用户管理主键
     * @return 结果
     */
    public int deleteSmsUserById(Long id);

    /**
     * 根据accessKey查询用户
     *
     * @param accessKey 访问密钥
     * @return 用户信息
     */
    public SmsUser getByAccessKey(String accessKey);

    /**
     * 扣减用户余额
     *
     * @param userId 用户ID
     * @param amount 扣减金额
     * @return 影响行数
     */
    public int deductBalance(Long userId, int amount);
}
