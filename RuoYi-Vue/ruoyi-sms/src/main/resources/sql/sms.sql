-- 短信用户表
CREATE TABLE `sys_sms_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `api_key` varchar(100) NOT NULL COMMENT 'API密钥',
  `secret_key` varchar(100) NOT NULL COMMENT '密钥',
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`),
  UNIQUE KEY `uk_api_key` (`api_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信用户表';

-- 短信发送日志表
CREATE TABLE `sys_sms_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `recipient` varchar(20) NOT NULL COMMENT '接收手机号',
  `content` varchar(500) NOT NULL COMMENT '短信内容',
  `status` char(1) NOT NULL COMMENT '发送状态（0失败 1成功）',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信发送日志表';
-- 删除旧的菜单项
DELETE FROM sys_menu WHERE menu_id IN (3100, 3101, 3102, 3103, 3104, 3105, 3106, 3107);

-- 重新添加到正确位置
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES(3100, '短信管理', '0', 5, 'sms', NULL, '', 1, 0, 'M', '0', '0', '', 'message', 'admin', NOW(), '', NULL, '短信管理目录');

-- 短信用户菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES(3101, '短信用户', '3100', 1, 'smsuser', 'sms/smsuser/index', '', 1, 0, 'C', '0', '0', 'sms:smsuser:list', 'user', 'admin', NOW(), '', NULL, '短信用户菜单');

-- 发送记录菜单
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES(3102, '发送记录', '3100', 2, 'log', 'sms/log/index', '', 1, 0, 'C', '0', '0', 'sms:log:list', 'logininfor', 'admin', NOW(), '', NULL, '发送记录菜单');

-- 按钮权限
INSERT INTO sys_menu(menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) 
VALUES
(3103, '短信用户查询', '3101', 1, '', '', '', 1, 0, 'F', '0', '0', 'sms:smsuser:query', '#', 'admin', NOW(), '', NULL, ''),
(3104, '短信用户新增', '3101', 2, '', '', '', 1, 0, 'F', '0', '0', 'sms:smsuser:add', '#', 'admin', NOW(), '', NULL, ''),
(3105, '短信用户修改', '3101', 3, '', '', '', 1, 0, 'F', '0', '0', 'sms:smsuser:edit', '#', 'admin', NOW(), '', NULL, ''),
(3106, '短信用户删除', '3101', 4, '', '', '', 1, 0, 'F', '0', '0', 'sms:smsuser:remove', '#', 'admin', NOW(), '', NULL, ''),
(3107, '短信用户导出', '3101', 5, '', '', '', 1, 0, 'F', '0', '0', 'sms:smsuser:export', '#', 'admin', NOW(), '', NULL, ''); 
