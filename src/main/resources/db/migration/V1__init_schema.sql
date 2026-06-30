-- 用户表
CREATE TABLE `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `openid` VARCHAR(64) NOT NULL COMMENT '微信openid',
    `unionid` VARCHAR(64) COMMENT '微信unionid',
    `nickname` VARCHAR(64) COMMENT '昵称',
    `avatar` VARCHAR(255) COMMENT '头像URL',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 日记表
CREATE TABLE `diaries` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `title` VARCHAR(255) COMMENT '标题',
    `content` TEXT COMMENT '正文内容',
    `mood` VARCHAR(20) COMMENT '心情：happy/excited/calm/sad/angry/tired/love/surprised',
    `type` VARCHAR(20) DEFAULT 'text' COMMENT '类型：text/voice/image/video/mixed',
    `voice` VARCHAR(255) COMMENT '语音文件URL',
    `voice_duration` INT COMMENT '语音时长（秒）',
    `video` VARCHAR(255) COMMENT '视频文件URL',
    `video_thumb` VARCHAR(255) COMMENT '视频缩略图URL',
    `diary_date` DATE COMMENT '日记日期',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` TINYINT DEFAULT 0 COMMENT '逻辑删除标记',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_diary_date` (`diary_date`),
    KEY `idx_mood` (`mood`),
    KEY `idx_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日记表';

-- 日记图片表
CREATE TABLE `diary_images` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `diary_id` BIGINT NOT NULL COMMENT '日记ID',
    `url` VARCHAR(255) NOT NULL COMMENT '图片URL',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    PRIMARY KEY (`id`),
    KEY `idx_diary_id` (`diary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日记图片表';

-- 标签表
CREATE TABLE `tags` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `name` VARCHAR(50) NOT NULL COMMENT '标签名',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_tag` (`user_id`, `name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

-- 日记标签关联表
CREATE TABLE `diary_tags` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `diary_id` BIGINT NOT NULL COMMENT '日记ID',
    `tag_id` BIGINT NOT NULL COMMENT '标签ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_diary_tag` (`diary_id`, `tag_id`),
    KEY `idx_tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='日记标签关联表';

-- AI分析记录表
CREATE TABLE `ai_analyses` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `diary_id` BIGINT NOT NULL COMMENT '日记ID',
    `mode` VARCHAR(50) NOT NULL COMMENT '分析模式',
    `content` TEXT COMMENT '分析结果',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_diary_id` (`diary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI分析记录表';

-- 用户设置表
CREATE TABLE `user_settings` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `theme` VARCHAR(20) DEFAULT 'light' COMMENT '主题：light/dark',
    `default_mood` VARCHAR(20) COMMENT '默认心情',
    `reminder_enabled` TINYINT DEFAULT 0 COMMENT '是否开启提醒',
    `reminder_time` VARCHAR(10) COMMENT '提醒时间 HH:mm',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户设置表';
