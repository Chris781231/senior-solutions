CREATE TABLE `activities` (
                              `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                              `description` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8_hungarian_ci',
                              `start_time` DATETIME(6) NULL DEFAULT NULL,
                              `type` INT(11) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
)
    COLLATE='utf8_hungarian_ci'
ENGINE=InnoDB;
