CREATE TABLE `therapy_sessions`
(
    `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `location`  VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_hungarian_ci',
    `therapist` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_hungarian_ci',
    `time`      DATETIME(6)  NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    COLLATE = 'utf8mb4_hungarian_ci'
    ENGINE = MyISAM;
