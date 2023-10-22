SET FOREIGN_KEY_CHECKS = 0;
SET SQL_SAFE_UPDATES = 0;

CREATE TABLE IF NOT EXISTS `flyway_schema_history` (
    `installed_rank` INT NOT NULL,
    `version` VARCHAR(50),
    `description` VARCHAR(200),
    `type` VARCHAR(20) NOT NULL,
    `script` VARCHAR(1000) NOT NULL,
    `checksum` INT,
    `installed_by` VARCHAR(100) NOT NULL,
    `installed_on` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `execution_time` INT NOT NULL,
    `success` BOOLEAN NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `roles` (
    `role_id` BIGINT NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `UK_role_name` (`role_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `users` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT,
    `active` BIT(1) DEFAULT NULL,
    `email` VARCHAR(100) DEFAULT NULL,
    `password` VARCHAR(100) NOT NULL,
    `user_name` VARCHAR(100) NOT NULL,
    `role_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `UK_user_name` (`user_name`),
    KEY `FK_role_id` (`role_id`),
    CONSTRAINT `FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `tags` (
    `tag_id` BIGINT NOT NULL AUTO_INCREMENT,
    `category` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`tag_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `blogs` (
    `blog_id` BIGINT NOT NULL AUTO_INCREMENT,
    `blog_content` VARCHAR(100) NOT NULL,
    `blog_title` VARCHAR(100) NOT NULL,
    `published_date` DATETIME NOT NULL,
    `user_id` BIGINT DEFAULT NULL,
    PRIMARY KEY (`blog_id`),
    KEY `FK_user_id` (`user_id`),
    CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `blogs_tags` (
    `blog_id` BIGINT NOT NULL,
    `tag_id` BIGINT NOT NULL,
    KEY `FK_tag_id` (`tag_id`),
    KEY `FK_blog_id` (`blog_id`),
    CONSTRAINT `FK_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`),
    CONSTRAINT `FK_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `comments` (
    `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
    `comment_content` VARCHAR(100) NOT NULL,
    `comment_date` DATETIME NOT NULL,
    `user_id` BIGINT,
    `blog_id` BIGINT,
    PRIMARY KEY (`comment_id`),
    CONSTRAINT `FK_user_id_comments` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `FK_blog_id_comments` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- turn on safety and FK checks
SET FOREIGN_KEY_CHECKS = 1;
SET SQL_SAFE_UPDATES = 1;
