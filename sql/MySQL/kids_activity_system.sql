-- ----------------------------
-- 亲子研学活动平台数据库初始化脚本
-- 数据库名称: kids_activity_system
-- 字符集: utf8mb4
-- 排序规则: utf8mb4_general_ci
-- 数据库引擎: InnoDB
-- ----------------------------

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS kids_activity_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 使用该数据库
USE kids_activity_system;

-- ----------------------------
-- 1. 活动分类表 (activity_category)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `activity_category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `category_name` VARCHAR(50) NOT NULL COMMENT '分类名称（如：自然探索、历史文化、科技体验）',
    `category_code` VARCHAR(20) NOT NULL COMMENT '分类编码（唯一）',
    `icon` VARCHAR(200) NULL COMMENT '分类图标URL',
    `sort_order` INT NULL DEFAULT 0 COMMENT '排序序号',
    `status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_category_code` (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='活动分类表';

-- 添加状态约束
ALTER TABLE `activity_category` ADD CONSTRAINT `ck_activity_category_status` CHECK (`status` IN ('0', '1'));

-- ----------------------------
-- 2. 活动表 (activity)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `activity` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `activity_title` VARCHAR(100) NOT NULL COMMENT '活动标题',
    `activity_code` VARCHAR(50) NOT NULL COMMENT '活动编号（唯一）',
    `category_id` BIGINT NOT NULL COMMENT '活动分类ID（外键）',
    `cover_image` VARCHAR(500) NULL COMMENT '封面图片URL',
    `description` TEXT NULL COMMENT '活动描述',
    `content` LONGTEXT NULL COMMENT '活动详细内容（富文本）',
    `activity_type` VARCHAR(20) NOT NULL DEFAULT 'outdoor' COMMENT '活动类型（outdoor户外、indoor室内、online线上）',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `end_time` DATETIME NOT NULL COMMENT '结束时间',
    `location` VARCHAR(200) NOT NULL COMMENT '活动地点',
    `max_participants` INT NOT NULL DEFAULT 100 COMMENT '最大参与人数',
    `current_participants` INT NOT NULL DEFAULT 0 COMMENT '当前已报名人数',
    `age_min` INT NULL DEFAULT 3 COMMENT '最小年龄限制',
    `age_max` INT NULL DEFAULT 18 COMMENT '最大年龄限制',
    `price` DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '活动价格（元）',
    `status` VARCHAR(20) NOT NULL DEFAULT 'draft' COMMENT '状态（draft草稿、published已发布、ongoing进行中、completed已结束、cancelled已取消）',
    `enrollment_condition` TEXT NULL COMMENT '报名条件说明',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_activity_code` (`activity_code`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_status` (`status`),
    KEY `idx_start_time` (`start_time`),
    FOREIGN KEY (`category_id`) REFERENCES `activity_category` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='活动表';

-- 添加约束
ALTER TABLE `activity` ADD CONSTRAINT `ck_activity_end_time` CHECK (`end_time` > `start_time`);
ALTER TABLE `activity` ADD CONSTRAINT `ck_activity_participants` CHECK (`current_participants` <= `max_participants`);
ALTER TABLE `activity` ADD CONSTRAINT `ck_activity_status` CHECK (`status` IN ('draft', 'published', 'ongoing', 'completed', 'cancelled'));

-- ----------------------------
-- 3. 家长信息表 (parent_info)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `parent_info` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '关联用户ID（外键，指向sys_user）',
    `parent_name` VARCHAR(50) NOT NULL COMMENT '家长姓名',
    `phone` VARCHAR(11) NOT NULL COMMENT '手机号',
    `email` VARCHAR(100) NULL COMMENT '邮箱',
    `id_card` VARCHAR(18) NULL COMMENT '身份证号',
    `address` VARCHAR(200) NULL COMMENT '家庭地址',
    `occupation` VARCHAR(50) NULL COMMENT '职业',
    `relation_type` VARCHAR(20) NOT NULL DEFAULT 'parent' COMMENT '与学生关系（parent父亲、mother母亲、guardian监护人）',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='家长信息表';

-- 添加约束
ALTER TABLE `parent_info` ADD CONSTRAINT `ck_parent_relation_type` CHECK (`relation_type` IN ('parent', 'mother', 'guardian'));
ALTER TABLE `parent_info` ADD CONSTRAINT `ck_parent_phone` CHECK (`phone` REGEXP '^1[3-9][0-9]{9}$');

-- ----------------------------
-- 4. 学生信息表 (student_info)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `student_info` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '关联用户ID（外键，指向sys_user）',
    `student_name` VARCHAR(50) NOT NULL COMMENT '学生姓名',
    `student_no` VARCHAR(30) NOT NULL COMMENT '学号/学生编号（唯一）',
    `gender` CHAR(1) NOT NULL COMMENT '性别（0男 1女）',
    `birthday` DATE NOT NULL COMMENT '出生日期',
    `avatar` VARCHAR(500) NULL COMMENT '头像URL',
    `school` VARCHAR(100) NULL COMMENT '学校名称',
    `grade` VARCHAR(20) NULL COMMENT '年级',
    `class_name` VARCHAR(20) NULL COMMENT '班级',
    `parent_id` BIGINT NULL COMMENT '家长ID（外键，指向parent_info）',
    `health_note` TEXT NULL COMMENT '健康备注/过敏史',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_student_no` (`student_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_parent_id` (`parent_id`),
    FOREIGN KEY (`parent_id`) REFERENCES `parent_info` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='学生信息表';

-- 添加约束
ALTER TABLE `student_info` ADD CONSTRAINT `ck_student_gender` CHECK (`gender` IN ('0', '1'));

-- ----------------------------
-- 5. 预约订单表 (appointment)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `appointment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_no` VARCHAR(32) NOT NULL COMMENT '订单编号（唯一，格式：APT+时间戳+随机数）',
    `activity_id` BIGINT NOT NULL COMMENT '活动ID（外键）',
    `student_id` BIGINT NOT NULL COMMENT '学生ID（外键）',
    `parent_id` BIGINT NOT NULL COMMENT '家长ID（外键）',
    `appointment_time` DATETIME NOT NULL COMMENT '预约时间',
    `participants_count` INT NOT NULL DEFAULT 1 COMMENT '参与人数',
    `total_amount` DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '订单总金额',
    `status` VARCHAR(20) NOT NULL DEFAULT 'pending' COMMENT '状态（pending待支付、paid已支付、cancelled已取消、refunded已退款）',
    `payment_time` DATETIME NULL COMMENT '支付时间',
    `cancel_time` DATETIME NULL COMMENT '取消时间',
    `cancel_reason` VARCHAR(200) NULL COMMENT '取消原因',
    `remark` VARCHAR(500) NULL COMMENT '预约备注',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_student_id` (`student_id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`),
    FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE RESTRICT,
    FOREIGN KEY (`student_id`) REFERENCES `student_info` (`id`) ON DELETE RESTRICT,
    FOREIGN KEY (`parent_id`) REFERENCES `parent_info` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='预约订单表';

-- 添加约束
ALTER TABLE `appointment` ADD CONSTRAINT `ck_appointment_participants` CHECK (`participants_count` > 0);
ALTER TABLE `appointment` ADD CONSTRAINT `ck_appointment_total_amount` CHECK (`total_amount` >= 0);
ALTER TABLE `appointment` ADD CONSTRAINT `ck_appointment_status` CHECK (`status` IN ('pending', 'paid', 'cancelled', 'refunded'));

-- ----------------------------
-- 6. 报名信息表 (enrollment)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `enrollment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `activity_id` BIGINT NOT NULL COMMENT '活动ID（外键）',
    `student_id` BIGINT NOT NULL COMMENT '学生ID（外键）',
    `appointment_id` BIGINT NULL COMMENT '关联预约订单ID（外键）',
    `parent_name` VARCHAR(50) NOT NULL COMMENT '家长姓名',
    `parent_phone` VARCHAR(11) NOT NULL COMMENT '家长手机号',
    `emergency_contact` VARCHAR(50) NULL COMMENT '紧急联系人',
    `emergency_phone` VARCHAR(11) NULL COMMENT '紧急联系电话',
    `enrollment_time` DATETIME NOT NULL COMMENT '报名时间',
    `checkin_status` VARCHAR(20) NOT NULL DEFAULT 'not_started' COMMENT '签到状态（not_started未开始、checked_in已签到、absent缺席）',
    `checkin_time` DATETIME NULL COMMENT '签到时间',
    `status` VARCHAR(20) NULL DEFAULT 'active' COMMENT '状态（active正常、cancelled已取消）',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_activity_student` (`activity_id`, `student_id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_student_id` (`student_id`),
    KEY `idx_appointment_id` (`appointment_id`),
    KEY `idx_checkin_status` (`checkin_status`),
    FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`student_id`) REFERENCES `student_info` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='报名信息表';

-- 添加约束
ALTER TABLE `enrollment` ADD CONSTRAINT `ck_enrollment_checkin_status` CHECK (`checkin_status` IN ('not_started', 'checked_in', 'absent'));
ALTER TABLE `enrollment` ADD CONSTRAINT `ck_enrollment_status` CHECK (`status` IN ('active', 'cancelled'));

-- ----------------------------
-- 7. 成长档案表 (growth_record)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `growth_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `student_id` BIGINT NOT NULL COMMENT '学生ID（外键）',
    `activity_id` BIGINT NULL COMMENT '关联活动ID（外键，可关联具体活动）',
    `title` VARCHAR(100) NOT NULL COMMENT '档案标题',
    `content` TEXT NULL COMMENT '档案内容描述',
    `record_type` VARCHAR(20) NOT NULL DEFAULT 'activity' COMMENT '记录类型（activity活动记录、growth成长记录、achievement成就记录）',
    `tags` VARCHAR(200) NULL COMMENT '标签（多个标签用逗号分隔）',
    `record_date` DATE NOT NULL COMMENT '记录日期',
    `visibility` VARCHAR(20) NOT NULL DEFAULT 'private' COMMENT '可见性（private私有、family家庭可见、public公开）',
    `like_count` INT NOT NULL DEFAULT 0 COMMENT '点赞数',
    `view_count` INT NOT NULL DEFAULT 0 COMMENT '浏览次数',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `idx_student_id` (`student_id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_record_type` (`record_type`),
    KEY `idx_record_date` (`record_date`),
    KEY `idx_create_time` (`create_time`),
    FOREIGN KEY (`student_id`) REFERENCES `student_info` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='成长档案表';

-- 添加约束
ALTER TABLE `growth_record` ADD CONSTRAINT `ck_growth_record_type` CHECK (`record_type` IN ('activity', 'growth', 'achievement'));
ALTER TABLE `growth_record` ADD CONSTRAINT `ck_growth_visibility` CHECK (`visibility` IN ('private', 'family', 'public'));

-- ----------------------------
-- 8. 成长附件表 (growth_attachment)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `growth_attachment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `growth_record_id` BIGINT NOT NULL COMMENT '成长档案ID（外键）',
    `file_name` VARCHAR(200) NOT NULL COMMENT '原始文件名',
    `file_path` VARCHAR(500) NOT NULL COMMENT '文件存储路径（MinIO URL或本地路径）',
    `file_type` VARCHAR(10) NOT NULL COMMENT '文件类型（image图片、video视频、document文档）',
    `file_size` BIGINT NOT NULL DEFAULT 0 COMMENT '文件大小（字节）',
    `thumbnail_path` VARCHAR(500) NULL COMMENT '缩略图路径',
    `mime_type` VARCHAR(100) NULL COMMENT 'MIME类型（如：image/jpeg、video/mp4）',
    `sort_order` INT NULL DEFAULT 0 COMMENT '排序序号',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `idx_growth_record_id` (`growth_record_id`),
    KEY `idx_file_type` (`file_type`),
    FOREIGN KEY (`growth_record_id`) REFERENCES `growth_record` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='成长附件表';

-- 添加约束
ALTER TABLE `growth_attachment` ADD CONSTRAINT `ck_growth_attachment_type` CHECK (`file_type` IN ('image', 'video', 'document'));

-- ----------------------------
-- 9. 签到记录表 (checkin_record)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `checkin_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `activity_id` BIGINT NOT NULL COMMENT '活动ID（外键）',
    `student_id` BIGINT NOT NULL COMMENT '学生ID（外键）',
    `enrollment_id` BIGINT NOT NULL COMMENT '报名记录ID（外键）',
    `checkin_time` DATETIME NOT NULL COMMENT '签到时间',
    `checkin_method` VARCHAR(20) NOT NULL DEFAULT 'manual' COMMENT '签到方式（manual手动、qrcode二维码、face人脸识别）',
    `checkin_location` VARCHAR(200) NULL COMMENT '签到地点',
    `latitude` DECIMAL(10,6) NULL COMMENT '签到纬度',
    `longitude` DECIMAL(10,6) NULL COMMENT '签到经度',
    `operator` VARCHAR(64) NULL COMMENT '操作人',
    `status` VARCHAR(20) NOT NULL DEFAULT 'success' COMMENT '状态（success成功、failed失败）',
    `failure_reason` VARCHAR(200) NULL COMMENT '失败原因',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `idx_activity_id` (`activity_id`),
    KEY `idx_student_id` (`student_id`),
    KEY `idx_enrollment_id` (`enrollment_id`),
    KEY `idx_checkin_time` (`checkin_time`),
    FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`student_id`) REFERENCES `student_info` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`enrollment_id`) REFERENCES `enrollment` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='签到记录表';

-- 添加约束
ALTER TABLE `checkin_record` ADD CONSTRAINT `ck_checkin_method` CHECK (`checkin_method` IN ('manual', 'qrcode', 'face'));
ALTER TABLE `checkin_record` ADD CONSTRAINT `ck_checkin_status` CHECK (`status` IN ('success', 'failed'));

-- ----------------------------
-- 10. 支付记录表 (payment_record)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `payment_record` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `payment_no` VARCHAR(32) NOT NULL COMMENT '支付单号（唯一，格式：PAY+时间戳+随机数）',
    `appointment_id` BIGINT NOT NULL COMMENT '预约订单ID（外键）',
    `order_no` VARCHAR(32) NOT NULL COMMENT '关联订单编号',
    `payment_type` VARCHAR(20) NOT NULL DEFAULT 'wechat' COMMENT '支付方式（wechat微信支付、alipay支付宝）',
    `payment_amount` DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '支付金额',
    `transaction_id` VARCHAR(64) NULL COMMENT '第三方交易流水号',
    `prepay_id` VARCHAR(64) NULL COMMENT '预支付交易会话标识',
    `payment_time` DATETIME NULL COMMENT '支付完成时间',
    `notify_time` DATETIME NULL COMMENT '支付回调通知时间',
    `status` VARCHAR(20) NOT NULL DEFAULT 'pending' COMMENT '状态（pending待支付、paid支付成功、failed支付失败、refunded已退款、refund_fail退款失败）',
    `failure_reason` VARCHAR(200) NULL COMMENT '失败原因',
    `refund_amount` DECIMAL(10,2) NULL COMMENT '退款金额',
    `refund_time` DATETIME NULL COMMENT '退款时间',
    `refund_reason` VARCHAR(200) NULL COMMENT '退款原因',
    `ip_address` VARCHAR(50) NULL COMMENT '支付IP地址',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_payment_no` (`payment_no`),
    KEY `idx_appointment_id` (`appointment_id`),
    KEY `idx_order_no` (`order_no`),
    KEY `idx_transaction_id` (`transaction_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`),
    FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='支付记录表';

-- 添加约束
ALTER TABLE `payment_record` ADD CONSTRAINT `ck_payment_type` CHECK (`payment_type` IN ('wechat', 'alipay'));
ALTER TABLE `payment_record` ADD CONSTRAINT `ck_payment_amount` CHECK (`payment_amount` > 0);
ALTER TABLE `payment_record` ADD CONSTRAINT `ck_payment_status` CHECK (`status` IN ('pending', 'paid', 'failed', 'refunded', 'refund_fail'));

-- ----------------------------
-- 11. 主办方信息表 (organizer_info)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `organizer_info` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '关联用户ID（外键，指向sys_user）',
    `organizer_name` VARCHAR(50) NOT NULL COMMENT '主办方姓名',
    `company_name` VARCHAR(100) NOT NULL COMMENT '公司/机构名称',
    `license_no` VARCHAR(50) NULL COMMENT '营业执照号',
    `phone` VARCHAR(11) NOT NULL COMMENT '联系电话',
    `email` VARCHAR(100) NULL COMMENT '邮箱',
    `address` VARCHAR(200) NULL COMMENT '地址',
    `qualification` VARCHAR(500) NULL COMMENT '资质证书（JSON格式存储）',
    `credit_score` INT NOT NULL DEFAULT 100 COMMENT '信用评分（0-100）',
    `total_activities` INT NOT NULL DEFAULT 0 COMMENT '发布活动总数',
    `total_participants` INT NOT NULL DEFAULT 0 COMMENT '累计参与人数',
    `status` VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '状态（active正常、suspended暂停、frozen冻结）',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_company_name` (`company_name`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='主办方信息表';

-- 添加约束
ALTER TABLE `organizer_info` ADD CONSTRAINT `ck_organizer_credit_score` CHECK (`credit_score` BETWEEN 0 AND 100);
ALTER TABLE `organizer_info` ADD CONSTRAINT `ck_organizer_status` CHECK (`status` IN ('active', 'suspended', 'frozen'));
ALTER TABLE `organizer_info` ADD CONSTRAINT `ck_organizer_phone` CHECK (`phone` REGEXP '^1[3-9][0-9]{9}$');

-- ----------------------------
-- 12. 平台管理员表 (platform_admin)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `platform_admin` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '关联用户ID（外键，指向sys_user）',
    `admin_name` VARCHAR(50) NOT NULL COMMENT '管理员姓名',
    `department` VARCHAR(50) NULL COMMENT '所属部门',
    `permissions` TEXT NULL COMMENT '权限列表（JSON格式）',
    `last_login_time` DATETIME NULL COMMENT '最后登录时间',
    `last_login_ip` VARCHAR(50) NULL COMMENT '最后登录IP',
    `login_count` INT NOT NULL DEFAULT 0 COMMENT '登录次数',
    `status` VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '状态（active正常、locked锁定）',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) NULL COMMENT '更新者',
    `update_time` DATETIME NULL COMMENT '更新时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除标志（0存在 2删除）',
    `remark` VARCHAR(500) NULL COMMENT '备注',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='平台管理员表';

-- 添加约束
ALTER TABLE `platform_admin` ADD CONSTRAINT `ck_admin_status` CHECK (`status` IN ('active', 'locked'));

-- ----------------------------
-- 13. 活动统计表 (activity_statistics)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `activity_statistics` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `activity_id` BIGINT NOT NULL COMMENT '活动ID（外键）',
    `organizer_id` BIGINT NOT NULL COMMENT '主办方ID（外键）',
    `participant_count` INT NOT NULL DEFAULT 0 COMMENT '参与人数',
    `checkin_count` INT NOT NULL DEFAULT 0 COMMENT '签到人数',
    `checkin_rate` DECIMAL(5,2) NOT NULL DEFAULT 0.00 COMMENT '签到率',
    `revenue` DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '收入金额',
    `satisfaction_score` DECIMAL(3,1) NULL COMMENT '满意度评分（0-10）',
    `statistics_date` DATE NOT NULL COMMENT '统计日期',
    `create_by` VARCHAR(64) NULL COMMENT '创建者',
    `create_time` DATETIME NULL COMMENT '创建时间',
    `update_time` DATETIME NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_activity_date` (`activity_id`, `statistics_date`),
    KEY `idx_organizer_id` (`organizer_id`),
    KEY `idx_statistics_date` (`statistics_date`),
    FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`organizer_id`) REFERENCES `organizer_info` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='活动统计表';

-- 添加约束
ALTER TABLE `activity_statistics` ADD CONSTRAINT `ck_activity_statistics_participants` CHECK (`participant_count` >= 0 AND `checkin_count` >= 0);
ALTER TABLE `activity_statistics` ADD CONSTRAINT `ck_activity_statistics_rate` CHECK (`checkin_rate` BETWEEN 0 AND 100);

-- ----------------------------
-- 14. 用户行为日志表 (user_behavior_log)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_behavior_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NULL COMMENT '用户ID（外键，指向sys_user）',
    `user_type` VARCHAR(20) NOT NULL COMMENT '用户类型（parent家长、student学生、organizer主办方、admin管理员）',
    `action_type` VARCHAR(50) NOT NULL COMMENT '行为类型（login登录、view浏览、reserve预约、cancel取消、checkin签到、publish发布等）',
    `resource_type` VARCHAR(30) NULL COMMENT '资源类型（activity活动、appointment订单、growth成长档案等）',
    `resource_id` BIGINT NULL COMMENT '资源ID',
    `action_detail` TEXT NULL COMMENT '行为详情（JSON格式）',
    `ip_address` VARCHAR(50) NULL COMMENT 'IP地址',
    `user_agent` VARCHAR(500) NULL COMMENT '浏览器User-Agent',
    `device_type` VARCHAR(20) NULL COMMENT '设备类型（mobile移动端、pc桌面端）',
    `action_time` DATETIME NOT NULL COMMENT '行为时间',
    `create_time` DATETIME NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_user_type` (`user_type`),
    KEY `idx_action_type` (`action_type`),
    KEY `idx_resource_type` (`resource_type`),
    KEY `idx_action_time` (`action_time`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户行为日志表';

-- 添加约束
ALTER TABLE `user_behavior_log` ADD CONSTRAINT `ck_user_behavior_type` CHECK (`user_type` IN ('parent', 'student', 'organizer', 'admin'));

-- ----------------------------
-- 15. 流量分析表 (traffic_analysis)
-- ----------------------------
CREATE TABLE IF NOT EXISTS `traffic_analysis` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `date` DATE NOT NULL COMMENT '日期',
    `hour` TINYINT NOT NULL COMMENT '小时（0-23）',
    `pv` BIGINT NOT NULL DEFAULT 0 COMMENT '页面浏览量',
    `uv` BIGINT NOT NULL DEFAULT 0 COMMENT '独立访客数',
    `avg_duration` DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '平均停留时长（秒）',
    `bounce_rate` DECIMAL(5,2) NOT NULL DEFAULT 0.00 COMMENT '跳出率',
    `active_users` INT NOT NULL DEFAULT 0 COMMENT '活跃用户数',
    `new_users` INT NOT NULL DEFAULT 0 COMMENT '新增用户数',
    `create_time` DATETIME NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_date_hour` (`date`, `hour`),
    KEY `idx_date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='流量分析表';

-- 添加约束
ALTER TABLE `traffic_analysis` ADD CONSTRAINT `ck_traffic_hour` CHECK (`hour` BETWEEN 0 AND 23);
ALTER TABLE `traffic_analysis` ADD CONSTRAINT `ck_traffic_bounce_rate` CHECK (`bounce_rate` BETWEEN 0 AND 100);
ALTER TABLE `traffic_analysis` ADD CONSTRAINT `ck_traffic_values` CHECK (`pv` >= 0 AND `uv` >= 0);

-- ----------------------------
-- 插入初始化数据
-- ----------------------------

-- 活动分类初始化数据
INSERT INTO `activity_category` (`category_name`, `category_code`, `sort_order`, `status`, `create_time`) VALUES
('自然探索', 'nature_explore', 1, '0', NOW()),
('历史文化', 'history_culture', 2, '0', NOW()),
('科技体验', 'science_experience', 3, '0', NOW()),
('艺术手工', 'art_handcraft', 4, '0', NOW()),
('体育运动', 'sports', 5, '0', NOW()),
('社会实践', 'social_practice', 6, '0', NOW());

-- 输出初始化完成信息
SELECT '亲子研学活动平台数据库初始化完成' as 'status';
