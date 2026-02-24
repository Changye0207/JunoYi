-- ----------------------------
-- 权限与角色配置脚本
-- 执行顺序: 先执行 junoyi.sql 创建表结构，再执行此脚本
-- ----------------------------

USE junoyi;

-- 禁用外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 1. 角色配置
-- ----------------------------

-- 平台管理员角色
INSERT IGNORE INTO `sys_role` (`role_name`, `role_key`, `sort`, `data_scope`, `status`, `del_flag`, `create_by`, `create_time`, `remark`)
VALUES ('平台管理员', 'platform_admin', 1, '1', 1, 0, 'super_admin', NOW(), '平台管理员角色，拥有平台所有权限');

-- 主办方角色
INSERT IGNORE INTO `sys_role` (`role_name`, `role_key`, `sort`, `data_scope`, `status`, `del_flag`, `create_by`, `create_time`, `remark`)
VALUES ('主办方', 'organizer', 2, '4', 1, 0, 'super_admin', NOW(), '主办方角色，拥有活动管理权限');

-- 亲子家庭角色
INSERT IGNORE INTO `sys_role` (`role_name`, `role_key`, `sort`, `data_scope`, `status`, `del_flag`, `create_by`, `create_time`, `remark`)
VALUES ('亲子家庭', 'parent_family', 3, '5', 1, 0, 'super_admin', NOW(), '亲子家庭角色，拥有活动报名和成长档案权限');

-- ----------------------------
-- 2. 权限组配置
-- ----------------------------

-- 平台管理员权限组
INSERT IGNORE INTO `sys_perm_group` (`group_code`, `group_name`, `priority`, `description`, `status`, `permissions`, `create_by`, `create_time`)
VALUES ('platform_admin_group', '平台管理员权限组', 1000, '平台管理员的所有权限', 1,
'["system.*", "activity.*", "user.*", "appointment.*", "growth.*", "payment.*", "statistics.*"]',
'super_admin', NOW());

-- 主办方权限组
INSERT IGNORE INTO `sys_perm_group` (`group_code`, `group_name`, `priority`, `description`, `status`, `permissions`, `create_by`, `create_time`)
VALUES ('organizer_group', '主办方权限组', 500, '主办方的活动管理权限', 1,
'["activity:activity:list", "activity:activity:query", "activity:activity:add", "activity:activity:edit", "activity:activity:publish", "activity:activity:offline", "statistics:activity:view"]',
'super_admin', NOW());

-- 亲子家庭权限组
INSERT IGNORE INTO `sys_perm_group` (`group_code`, `group_name`, `priority`, `description`, `status`, `permissions`, `create_by`, `create_time`)
VALUES ('parent_family_group', '亲子家庭权限组', 100, '亲子家庭的活动报名和成长档案权限', 1,
'["activity:activity:list", "activity:activity:query", "appointment:appointment:add", "appointment:appointment:query", "appointment:appointment:cancel", "growth:record:list", "growth:record:query", "growth:record:add", "growth:record:edit", "growth:record:delete"]',
'super_admin', NOW());

-- ----------------------------
-- 3. 角色与权限组关联
-- ----------------------------

-- 平台管理员角色关联平台管理员权限组
INSERT IGNORE INTO `sys_role_group` (`role_id`, `group_id`)
SELECT r.id, g.id FROM sys_role r, sys_perm_group g
WHERE r.role_key = 'platform_admin' AND g.group_code = 'platform_admin_group';

-- 主办方角色关联主办方权限组
INSERT IGNORE INTO `sys_role_group` (`role_id`, `group_id`)
SELECT r.id, g.id FROM sys_role r, sys_perm_group g
WHERE r.role_key = 'organizer' AND g.group_code = 'organizer_group';

-- 亲子家庭角色关联亲子家庭权限组
INSERT IGNORE INTO `sys_role_group` (`role_id`, `group_id`)
SELECT r.id, g.id FROM sys_role r, sys_perm_group g
WHERE r.role_key = 'parent_family' AND g.group_code = 'parent_family_group';

-- ----------------------------
-- 4. 菜单配置
-- ----------------------------

-- 系统菜单（管理员菜单）
INSERT IGNORE INTO `sys_menu` (`parent_id`, `name`, `path`, `component`, `title`, `icon`, `menu_type`, `sort`, `permission`, `status`, `create_by`, `create_time`)
VALUES
(0, '系统管理', '/system', 'Layout', '系统管理', 'system', 0, 1, 'system.ui.system.view', 1, 'super_admin', NOW()),
(1, '用户管理', '/system/user', 'system/user/index', '用户管理', 'user', 1, 1, 'system.ui.user.view', 1, 'super_admin', NOW()),
(1, '角色管理', '/system/role', 'system/role/index', '角色管理', 'role', 1, 2, 'system.ui.role.view', 1, 'super_admin', NOW()),
(1, '权限管理', '/system/permission', 'system/permission/index', '权限管理', 'permission', 1, 3, 'system.ui.permission.view', 1, 'super_admin', NOW()),
(1, '菜单管理', '/system/menu', 'system/menu/index', '菜单管理', 'menu', 1, 4, 'system.ui.menu.view', 1, 'super_admin', NOW()),
(1, '字典管理', '/system/dict', 'system/dict/index', '字典管理', 'dict', 1, 5, 'system.ui.dict.view', 1, 'super_admin', NOW()),
(1, '参数配置', '/system/config', 'system/config/index', '参数配置', 'config', 1, 6, 'system.ui.config.view', 1, 'super_admin', NOW()),
(1, '部门管理', '/system/dept', 'system/dept/index', '部门管理', 'dept', 1, 7, 'system.ui.dept.view', 1, 'super_admin', NOW()),
(1, '会话管理', '/system/session', 'system/session/index', '会话管理', 'session', 1, 8, 'system.ui.session.view', 1, 'super_admin', NOW()),
(1, '操作日志', '/system/operlog', 'system/operlog/index', '操作日志', 'operlog', 1, 9, 'system.ui.oper-log.view', 1, 'super_admin', NOW()),
(1, '登录日志', '/system/authlog', 'system/authlog/index', '登录日志', 'authlog', 1, 10, 'system.ui.auth-log.view', 1, 'super_admin', NOW()),
(1, '缓存管理', '/system/cache', 'system/cache/index', '缓存管理', 'cache', 1, 11, 'system.ui.cache.view', 1, 'super_admin', NOW()),
(1, '系统信息', '/system/info', 'system/info/index', '系统信息', 'info', 1, 12, 'system.ui.info.view', 1, 'super_admin', NOW());

-- 主办方菜单
INSERT IGNORE INTO `sys_menu` (`parent_id`, `name`, `path`, `component`, `title`, `icon`, `menu_type`, `sort`, `permission`, `status`, `create_by`, `create_time`)
VALUES
(0, '活动管理', '/organizer/activity', 'Layout', '活动管理', 'activity', 0, 1, 'activity.ui.organizer.view', 1, 'super_admin', NOW()),
(13, '我的活动', '/organizer/activity/list', 'organizer/activity/index', '我的活动', 'list', 1, 1, 'activity.ui.organizer.activity.view', 1, 'super_admin', NOW()),
(13, '发布活动', '/organizer/activity/add', 'organizer/activity/add', '发布活动', 'add', 1, 2, 'activity.ui.organizer.activity.add', 1, 'super_admin', NOW()),
(13, '活动统计', '/organizer/activity/statistics', 'organizer/activity/statistics', '活动统计', 'statistics', 1, 3, 'activity.ui.organizer.activity.statistics', 1, 'super_admin', NOW());

-- 亲子家庭菜单
INSERT IGNORE INTO `sys_menu` (`parent_id`, `name`, `path`, `component`, `title`, `icon`, `menu_type`, `sort`, `permission`, `status`, `create_by`, `create_time`)
VALUES
(0, '亲子首页', '/parent/home', 'Layout', '亲子首页', 'home', 0, 1, 'parent.ui.home.view', 1, 'super_admin', NOW()),
(17, '活动列表', '/parent/activity/list', 'parent/activity/index', '活动列表', 'list', 1, 1, 'parent.ui.activity.view', 1, 'super_admin', NOW()),
(17, '我的预约', '/parent/appointment/list', 'parent/appointment/index', '我的预约', 'appointment', 1, 2, 'parent.ui.appointment.view', 1, 'super_admin', NOW()),
(17, '成长档案', '/parent/growth/record', 'parent/growth/index', '成长档案', 'growth', 1, 3, 'parent.ui.growth.view', 1, 'super_admin', NOW());

-- ----------------------------
-- 5. 权限配置
-- ----------------------------

-- 平台管理员权限
INSERT IGNORE INTO `sys_permission` (`permission`, `description`, `status`, `create_by`, `create_time`)
VALUES
('platform.ui.dashboard.view', '平台仪表盘页面权限', 1, 'super_admin', NOW()),
('platform.ui.activity.management', '平台活动管理页面权限', 1, 'super_admin', NOW()),
('platform.ui.user.management', '平台用户管理页面权限', 1, 'super_admin', NOW()),
('platform.ui.statistics.view', '平台统计页面权限', 1, 'super_admin', NOW());

-- 主办方权限
INSERT IGNORE INTO `sys_permission` (`permission`, `description`, `status`, `create_by`, `create_time`)
VALUES
('activity.ui.organizer.view', '主办方管理页面权限', 1, 'super_admin', NOW()),
('activity.ui.organizer.activity.view', '主办方活动列表页面权限', 1, 'super_admin', NOW()),
('activity.ui.organizer.activity.add', '主办方发布活动页面权限', 1, 'super_admin', NOW()),
('activity.ui.organizer.activity.statistics', '主办方活动统计页面权限', 1, 'super_admin', NOW());

-- 亲子家庭权限
INSERT IGNORE INTO `sys_permission` (`permission`, `description`, `status`, `create_by`, `create_time`)
VALUES
('parent.ui.home.view', '亲子首页页面权限', 1, 'super_admin', NOW()),
('parent.ui.activity.view', '亲子活动列表页面权限', 1, 'super_admin', NOW()),
('parent.ui.appointment.view', '亲子预约列表页面权限', 1, 'super_admin', NOW()),
('parent.ui.growth.view', '亲子成长档案页面权限', 1, 'super_admin', NOW());

-- ----------------------------
-- 6. 权限组权限配置
-- ----------------------------

-- 更新平台管理员权限组
UPDATE `sys_perm_group`
SET `permissions` = '["system.*", "platform.*", "activity.*", "user.*", "appointment.*", "growth.*", "payment.*", "statistics.*"]'
WHERE `group_code` = 'platform_admin_group';

-- 更新主办方权限组
UPDATE `sys_perm_group`
SET `permissions` = '["activity.ui.organizer.view", "activity.ui.organizer.activity.view", "activity.ui.organizer.activity.add", "activity.ui.organizer.activity.statistics", "activity.api.organizer.activity.*"]'
WHERE `group_code` = 'organizer_group';

-- 更新亲子家庭权限组
UPDATE `sys_perm_group`
SET `permissions` = '["parent.ui.home.view", "parent.ui.activity.view", "parent.ui.appointment.view", "parent.ui.growth.view", "activity.api.parent.activity.*", "appointment.api.parent.appointment.*", "growth.api.parent.growth.*"]'
WHERE `group_code` = 'parent_family_group';

-- ----------------------------
-- 7. 启用外键检查
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

-- 输出初始化完成信息
SELECT '权限与角色配置完成' as 'status';
