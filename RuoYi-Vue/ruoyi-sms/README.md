# 短信服务模块

## 接口说明

### 1. 发送短信
- 请求路径：/sms/send
- 请求方式：POST
- 请求参数：
```json
{
    "accessKey": "API密钥",
    "phoneNumbers": "手机号,多个逗号分隔",
    "templateCode": "模板编码",
    "templateParams": {
        "code": "验证码",
        "product": "产品名"
    },
    "timestamp": "时间戳",
    "nonce": "随机字符串",
    "sign": "签名"
}
```
- 响应结果：
```json
{
    "code": 200,
    "msg": "success",
    "data": {
        "messageId": "消息ID",
        "fee": "计费条数",
        "status": "发送状态",
        "message": "状态说明"
    }
}
```

### 2. 查询余额
- 请求路径：/sms/balance
- 请求方式：GET
- 请求头：
  - X-Access-Key: API密钥
  - X-Timestamp: 时间戳
  - X-Nonce: 随机字符串
  - X-Sign: 签名
- 响应结果：
```json
{
    "code": 200,
    "msg": "success", 
    "data": {
        "balance": "剩余条数"
    }
}
``` 