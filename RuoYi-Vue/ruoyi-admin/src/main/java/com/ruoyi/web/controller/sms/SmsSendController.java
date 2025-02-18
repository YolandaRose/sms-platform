package com.ruoyi.web.controller.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.domain.request.SmsSendRequest;
import com.ruoyi.sms.domain.response.SmsSendResult;
import com.ruoyi.sms.service.ISmsService;
import com.ruoyi.sms.service.ISmsUserService;
import com.ruoyi.common.utils.sms.SignUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sms/send")
public class SmsSendController extends BaseController {

    @Autowired
    private ISmsService smsService;

    @Autowired
    private ISmsUserService smsUserService;

    @PostMapping
    public AjaxResult send(@RequestBody SmsSendRequest request) {
        try {
            // 1. 根据 apiKey 获取用户信息
            SmsUser smsUser = smsUserService.getByAccessKey(request.getApiKey());
            if (smsUser == null) {
                return AjaxResult.error("无效的AccessKey");
            }

            // 2. 验证签名
            if (!SignUtils.validateSign(
                request.getApiKey(),     // API密钥
                request.getTimestamp(),  // 时间戳
                request.getNonce(),      // 随机数
                request.getSign(),       // 客户端签名
                smsUser.getSecretKey()   // 安全密钥
            )) {
                return AjaxResult.error("签名验证失败");
            }

            // 3. 发送短信
            SmsSendResult result = smsService.send(request, smsUser);

            // 4. 记录日志
            smsService.saveLog(request, result, smsUser);

            // 5. 扣除余额
            if ("success".equals(result.getStatus())) {
                smsUserService.deductBalance(smsUser.getId(),
                    Integer.parseInt(String.valueOf(result.getFee())));
            }

            return AjaxResult.success(result);
        } catch (Exception e) {
            log.error("发送短信异常", e);
            return AjaxResult.error(e.getMessage());
        }
    }
}
