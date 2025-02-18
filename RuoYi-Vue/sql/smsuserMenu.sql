-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('短信平台用户管理', '2000', '1', 'smsuser', 'sms/smsuser/index', 1, 0, 'C', '0', '0', 'sms:smsuser:list', '#', 'admin', sysdate(), '', null, '短信平台用户管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('短信平台用户管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'sms:smsuser:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('短信平台用户管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'sms:smsuser:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('短信平台用户管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'sms:smsuser:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('短信平台用户管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'sms:smsuser:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('短信平台用户管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'sms:smsuser:export',       '#', 'admin', sysdate(), '', null, '');