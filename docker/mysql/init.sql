-- 创建数据库
CREATE DATABASE IF NOT EXISTS campus_guide DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE campus_guide;

-- 创建用户并授权
CREATE USER IF NOT EXISTS 'campus_user'@'%' IDENTIFIED BY 'campus_password';
GRANT ALL PRIVILEGES ON campus_guide.* TO 'campus_user'@'%';
FLUSH PRIVILEGES;