CREATE TABLE `participants`
(
    `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_hungarian_ci',
    PRIMARY KEY (`id`)
)
    COLLATE = 'utf8mb4_hungarian_ci'
    ENGINE = MyISAM;