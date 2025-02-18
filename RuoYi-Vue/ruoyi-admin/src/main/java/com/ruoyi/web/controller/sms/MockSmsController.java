package com.ruoyi.web.controller.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sms.domain.SmsLog;
import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.service.ISmsLogService;
import com.ruoyi.sms.service.ISmsUserService;

@RestController
@RequestMapping("/mock/sms")
public class MockSmsController {

    private static final Logger logger = LoggerFactory.getLogger(MockSmsController.class);

    @Autowired
    private ISmsLogService smsLogService;

    @Autowired
    private ISmsUserService smsUserService;

    @PostMapping("/send")
    public AjaxResult mockSend(@RequestBody Map<String, String> params) {
        logger.info("=== 开始处理模拟短信请求 ===");
        logger.info("请求参数: {}", params);

        // 打印关键参数
        String account = params.get("account");
        String apiKey = params.get("apiKey");  // 获取 API 密钥
        logger.info("账号: {}", account);
        logger.info("API密钥: {}", apiKey);
        logger.info("手机号: {}", params.get("recipient"));
        logger.info("内容: {}", params.get("message"));

        // 构建日志对象（提前创建，用于记录所有情况）
        SmsLog smsLog = new SmsLog();
        smsLog.setRecipient(params.get("recipient"));
        smsLog.setContent(params.get("message"));
        smsLog.setSendTime(new Date());

        try {
            // 根据 account 查询用户
            SmsUser queryUser = new SmsUser();
            queryUser.setAccount(account);
            List<SmsUser> users = smsUserService.selectSmsUserList(queryUser);
            if (users.isEmpty()) {
                smsLog.setStatus("0");
                smsLog.setRemark("用户不存在");
                smsLogService.insertSmsLog(smsLog);
                return AjaxResult.error("用户不存在");
            }
            SmsUser user = users.get(0);
            
            // 验证 API 密钥
            if (!user.getApiKey().equals(apiKey)) {
                smsLog.setStatus("0");
                smsLog.setRemark("API密钥错误");
                smsLog.setUserId(user.getId());
                smsLogService.insertSmsLog(smsLog);
                return AjaxResult.error("API密钥错误");
            }

            smsLog.setUserId(user.getId());
            logger.info("用户ID: {}, 账号: {}, 当前余额: {}", user.getId(), user.getAccount(), user.getBalance());

            // 扣减短信余额
            int deducted = smsUserService.deductBalance(user.getId(), 1);
            if (deducted <= 0) {
                smsLog.setStatus("0");
                smsLog.setRemark("短信余额不足");
                smsLogService.insertSmsLog(smsLog);
                return AjaxResult.error("短信余额不足");
            }

            // 模拟延迟
            try {
                Thread.sleep(100);
                logger.info("模拟发送延迟: 100ms");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("模拟延迟被中断", e);
                smsLog.setStatus("0");
                smsLog.setRemark("发送被中断: " + e.getMessage());
                smsLogService.insertSmsLog(smsLog);
                return AjaxResult.error("发送被中断");
            }

            // 模拟发送成功
            smsLog.setStatus("1");
            smsLog.setRemark("模拟发送成功");
            smsLogService.insertSmsLog(smsLog);

            Map<String, String> response = new HashMap<>();
            response.put("code", "0000");
            response.put("message", "success");

            logger.info("=== 模拟短信请求处理完成 ===");
            logger.info("响应结果: {}", response);

            return AjaxResult.success(response);
        } catch (Exception e) {
            logger.error("发送短信异常", e);
            smsLog.setStatus("0");
            smsLog.setRemark("发送异常: " + e.getMessage());
            smsLogService.insertSmsLog(smsLog);
            return AjaxResult.error(e.getMessage());
        }
    }
}
