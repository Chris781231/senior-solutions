CREATE TABLE `dogs`
(
    `id`       BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `age`      INT(11)      NOT NULL,
    `breed`    VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_hungarian_ci',
    `fav_game` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_hungarian_ci',
    `name`     VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_hungarian_ci',
    PRIMARY KEY (`id`)
)
    COLLATE = 'utf8mb4_hungarian_ci'
    ENGINE = MyISAM;
