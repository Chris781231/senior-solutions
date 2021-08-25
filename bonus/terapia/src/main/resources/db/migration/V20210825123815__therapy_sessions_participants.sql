CREATE TABLE `therapy_sessions_participants`
(
    `therapy_sessions_id` BIGINT(20) NOT NULL,
    `participants_id`     BIGINT(20) NOT NULL,
    INDEX `FKg81xy2dq303c6asmcjdir4ugq` (`participants_id`),
    INDEX `FKotfp2pe8wm9fx5g1w5r52x09l` (`therapy_sessions_id`),
    CONSTRAINT `FKg81xy2dq303c6asmcjdir4ugq` FOREIGN KEY (`participants_id`) REFERENCES `therapy`.`participants` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT `FKotfp2pe8wm9fx5g1w5r52x09l` FOREIGN KEY (`therapy_sessions_id`) REFERENCES `therapy`.`therapy_sessions` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
    COLLATE = 'utf8mb4_hungarian_ci'
    ENGINE = MyISAM
;
