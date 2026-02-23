-- ----------------------------
-- 亲子研学活动平台数据库初始化数据脚本
-- 数据库名称: kids_activity_system
-- 执行顺序: 先执行 kids_activity_system.sql 创建表结构，再执行此脚本插入数据
-- ----------------------------

-- 使用该数据库
USE kids_activity_system;

-- 禁用外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 按顺序清空表数据（先清空引用其他表的表）
DELETE FROM `traffic_analysis`;
DELETE FROM `user_behavior_log`;
DELETE FROM `activity_statistics`;
DELETE FROM `growth_attachment`;
DELETE FROM `growth_record`;
DELETE FROM `checkin_record`;
DELETE FROM `payment_record`;
DELETE FROM `enrollment`;
DELETE FROM `appointment`;
DELETE FROM `activity`;
DELETE FROM `student_info`;
DELETE FROM `organizer_info`;
DELETE FROM `parent_info`;
DELETE FROM `platform_admin`;
DELETE FROM `activity_category`;

-- 重置自增主键
ALTER TABLE `activity_category` AUTO_INCREMENT = 1;
ALTER TABLE `activity` AUTO_INCREMENT = 1;
ALTER TABLE `appointment` AUTO_INCREMENT = 1;
ALTER TABLE `enrollment` AUTO_INCREMENT = 1;
ALTER TABLE `checkin_record` AUTO_INCREMENT = 1;
ALTER TABLE `payment_record` AUTO_INCREMENT = 1;
ALTER TABLE `student_info` AUTO_INCREMENT = 1;
ALTER TABLE `parent_info` AUTO_INCREMENT = 1;
ALTER TABLE `organizer_info` AUTO_INCREMENT = 1;
ALTER TABLE `platform_admin` AUTO_INCREMENT = 1;
ALTER TABLE `growth_record` AUTO_INCREMENT = 1;
ALTER TABLE `growth_attachment` AUTO_INCREMENT = 1;
ALTER TABLE `activity_statistics` AUTO_INCREMENT = 1;
ALTER TABLE `user_behavior_log` AUTO_INCREMENT = 1;
ALTER TABLE `traffic_analysis` AUTO_INCREMENT = 1;

-- ----------------------------
-- 活动分类表 (activity_category) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `activity_category` (`category_name`, `category_code`, `sort_order`, `status`, `create_time`) VALUES
('自然探索', 'nature_explore', 1, '0', NOW()),
('历史文化', 'history_culture', 2, '0', NOW()),
('科技体验', 'science_experience', 3, '0', NOW()),
('艺术手工', 'art_handcraft', 4, '0', NOW()),
('体育运动', 'sports', 5, '0', NOW()),
('社会实践', 'social_practice', 6, '0', NOW());

-- ----------------------------
-- 家长信息表 (parent_info) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `parent_info` (`user_id`, `parent_name`, `phone`, `email`, `id_card`, `address`, `occupation`, `relation_type`, `create_time`) VALUES
(1, '张三', '13800138001', 'zhangsan@example.com', '110101199001011234', '北京市朝阳区', '工程师', 'parent', NOW()),
(2, '李四', '13800138002', 'lisi@example.com', '110101199102022345', '上海市浦东新区', '教师', 'mother', NOW()),
(3, '王五', '13800138003', 'wangwu@example.com', '110101198903033456', '广州市天河区', '医生', 'guardian', NOW());

-- ----------------------------
-- 学生信息表 (student_info) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `student_info` (`user_id`, `student_name`, `student_no`, `gender`, `birthday`, `avatar`, `school`, `grade`, `class_name`, `parent_id`, `create_time`) VALUES
(101, '张小明', '2023001', '0', '2018-03-15', 'https://example.com/avatar1.jpg', '北京市实验小学', '三年级', '三班', 1, NOW()),
(102, '李小华', '2023002', '1', '2019-05-20', 'https://example.com/avatar2.jpg', '上海市第一小学', '二年级', '一班', 2, NOW()),
(103, '王小强', '2023003', '0', '2017-09-10', 'https://example.com/avatar3.jpg', '广州市天河区小学', '四年级', '二班', 3, NOW());

-- ----------------------------
-- 主办方信息表 (organizer_info) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `organizer_info` (`user_id`, `organizer_name`, `company_name`, `license_no`, `phone`, `email`, `address`, `qualification`, `credit_score`, `total_activities`, `total_participants`, `status`, `create_time`) VALUES
(201, '赵老师', '北京自然探索教育科技有限公司', '11010120230001', '13900139001', 'zhaolao shi@example.com', '北京市海淀区', '{"certificate1": "https://example.com/cert1.jpg"}', 100, 5, 200, 'active', NOW()),
(202, '钱老师', '上海历史文化传播中心', '31010120230002', '13900139002', 'qianlaoshi@example.com', '上海市黄浦区', '{"certificate2": "https://example.com/cert2.jpg"}', 95, 3, 150, 'active', NOW());

-- ----------------------------
-- 活动表 (activity) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `activity` (`activity_title`, `activity_code`, `category_id`, `cover_image`, `description`, `content`, `activity_type`, `start_time`, `end_time`, `location`, `max_participants`, `current_participants`, `age_min`, `age_max`, `price`, `status`, `create_time`) VALUES
('亲子自然探索一日游', 'ACT20231001', 1, 'https://example.com/activity1.jpg', '一起探索大自然的奥秘', '<p>活动详情内容...</p>', 'outdoor', '2023-10-01 09:00:00', '2023-10-01 17:00:00', '北京市怀柔区', 50, 20, 5, 12, 199.00, 'published', NOW()),
('博物馆奇妙夜', 'ACT20231002', 2, 'https://example.com/activity2.jpg', '夜宿博物馆，探索历史文化', '<p>活动详情内容...</p>', 'indoor', '2023-10-15 18:00:00', '2023-10-16 09:00:00', '上海市博物馆', 30, 15, 6, 14, 299.00, 'published', NOW()),
('科技体验营', 'ACT20231101', 3, 'https://example.com/activity3.jpg', '探索科技的魅力', '<p>活动详情内容...</p>', 'indoor', '2023-11-05 09:00:00', '2023-11-05 16:00:00', '广州市科学中心', 40, 25, 7, 15, 159.00, 'draft', NOW());

-- ----------------------------
-- 预约订单表 (appointment) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `appointment` (`order_no`, `activity_id`, `student_id`, `parent_id`, `appointment_time`, `participants_count`, `total_amount`, `status`, `create_time`) VALUES
('APT202310010001', 1, 1, 1, '2023-09-20 10:30:00', 1, 199.00, 'paid', NOW()),
('APT202310010002', 1, 2, 2, '2023-09-21 14:20:00', 1, 199.00, 'pending', NOW()),
('APT202310020001', 2, 3, 3, '2023-09-25 09:15:00', 1, 299.00, 'paid', NOW());

-- ----------------------------
-- 报名信息表 (enrollment) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `enrollment` (`activity_id`, `student_id`, `appointment_id`, `parent_name`, `parent_phone`, `emergency_contact`, `emergency_phone`, `enrollment_time`, `checkin_status`, `status`, `create_time`) VALUES
(1, 1, 1, '张三', '13800138001', '李四', '13800138002', '2023-09-20 10:30:00', 'not_started', 'active', NOW()),
(1, 2, 2, '李四', '13800138002', '王五', '13800138003', '2023-09-21 14:20:00', 'not_started', 'active', NOW()),
(2, 3, 3, '王五', '13800138003', '赵六', '13800138004', '2023-09-25 09:15:00', 'not_started', 'active', NOW());

-- ----------------------------
-- 成长档案表 (growth_record) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `growth_record` (`student_id`, `activity_id`, `title`, `content`, `record_type`, `tags`, `record_date`, `visibility`, `create_time`) VALUES
(1, 1, '自然探索活动记录', '今天参加了自然探索活动，学到了很多知识...', 'activity', '自然,探索,学习', '2023-10-01', 'family', NOW()),
(2, NULL, '绘画作品', '今天完成了一幅美丽的画作...', 'growth', '绘画,艺术', '2023-09-15', 'private', NOW()),
(3, NULL, '数学竞赛获奖', '在数学竞赛中获得了二等奖...', 'achievement', '数学,竞赛,获奖', '2023-09-20', 'public', NOW());

-- ----------------------------
-- 成长附件表 (growth_attachment) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `growth_attachment` (`growth_record_id`, `file_name`, `file_path`, `file_type`, `file_size`, `thumbnail_path`, `mime_type`, `sort_order`, `create_time`) VALUES
(1, 'activity_photo1.jpg', 'https://example.com/photo1.jpg', 'image', 2048000, 'https://example.com/thumb1.jpg', 'image/jpeg', 1, NOW()),
(1, 'activity_photo2.jpg', 'https://example.com/photo2.jpg', 'image', 1890000, 'https://example.com/thumb2.jpg', 'image/jpeg', 2, NOW()),
(2, 'painting.jpg', 'https://example.com/painting.jpg', 'image', 3000000, 'https://example.com/thumb3.jpg', 'image/jpeg', 1, NOW());

-- ----------------------------
-- 签到记录表 (checkin_record) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `checkin_record` (`activity_id`, `student_id`, `enrollment_id`, `checkin_time`, `checkin_method`, `checkin_location`, `latitude`, `longitude`, `operator`, `status`, `create_time`) VALUES
(1, 1, 1, '2023-10-01 08:55:00', 'qrcode', '北京市怀柔区自然公园', 40.3456, 116.4567, '赵老师', 'success', NOW());

-- ----------------------------
-- 支付记录表 (payment_record) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `payment_record` (`payment_no`, `appointment_id`, `order_no`, `payment_type`, `payment_amount`, `transaction_id`, `status`, `payment_time`, `create_time`) VALUES
('PAY202310010001', 1, 'APT202310010001', 'wechat', 199.00, 'wx2023100110301234567890', 'paid', '2023-09-20 10:35:00', NOW()),
('PAY202310020001', 3, 'APT202310020001', 'alipay', 299.00, 'alipay2023100209151234567890', 'paid', '2023-09-25 09:20:00', NOW());

-- ----------------------------
-- 平台管理员表 (platform_admin) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `platform_admin` (`user_id`, `admin_name`, `department`, `permissions`, `last_login_time`, `last_login_ip`, `login_count`, `status`, `create_time`) VALUES
(301, '管理员1', '系统管理部', '{"permissions": ["user_management", "activity_management", "order_management"]}', '2023-09-30 14:20:00', '192.168.1.100', 50, 'active', NOW()),
(302, '管理员2', '内容审核部', '{"permissions": ["content_review", "complaint_handling"]}', '2023-09-29 10:15:00', '192.168.1.101', 35, 'active', NOW());

-- ----------------------------
-- 活动统计表 (activity_statistics) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `activity_statistics` (`activity_id`, `organizer_id`, `participant_count`, `checkin_count`, `checkin_rate`, `revenue`, `satisfaction_score`, `statistics_date`, `create_time`, `update_time`) VALUES
(1, 1, 20, 18, 90.00, 3980.00, 9.5, '2023-10-01', NOW(), NOW()),
(2, 2, 15, 14, 93.33, 4485.00, 9.2, '2023-10-02', NOW(), NOW());

-- ----------------------------
-- 用户行为日志表 (user_behavior_log) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `user_behavior_log` (`user_id`, `user_type`, `action_type`, `resource_type`, `resource_id`, `action_detail`, `ip_address`, `user_agent`, `device_type`, `action_time`, `create_time`) VALUES
(1, 'parent', 'login', NULL, NULL, '{"login_method": "password"}', '192.168.1.105', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36', 'pc', '2023-09-30 10:00:00', NOW()),
(101, 'student', 'view', 'activity', 1, '{"duration": 300}', '192.168.1.106', 'Mozilla/5.0 (iPhone; CPU iPhone OS 16_0 like Mac OS X)', 'mobile', '2023-09-30 10:15:00', NOW()),
(201, 'organizer', 'publish', 'activity', 3, '{"activity_id": 3}', '192.168.1.107', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/116.0.0.0', 'pc', '2023-09-30 14:00:00', NOW());

-- ----------------------------
-- 流量分析表 (traffic_analysis) 初始化数据
-- ----------------------------
INSERT IGNORE INTO `traffic_analysis` (`date`, `hour`, `pv`, `uv`, `avg_duration`, `bounce_rate`, `active_users`, `new_users`, `create_time`) VALUES
('2023-09-30', 9, 150, 80, 180.5, 35.00, 60, 10, NOW()),
('2023-09-30', 10, 200, 100, 210.3, 30.00, 75, 15, NOW()),
('2023-09-30', 11, 180, 90, 195.8, 32.00, 70, 12, NOW()),
('2023-09-30', 14, 250, 120, 240.2, 28.00, 85, 18, NOW());

-- 启用外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- 输出初始化完成信息
SELECT '亲子研学活动平台数据库初始化数据完成' as 'status';
