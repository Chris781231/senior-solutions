DROP TABLE IF EXISTS  `locations`;
CREATE TABLE `locations` (
    `id` bigint(20) AUTO_INCREMENT,
    `name` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
    `lat` double(10,2) DEFAULT NULL,
    `lon` double(10,2) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;