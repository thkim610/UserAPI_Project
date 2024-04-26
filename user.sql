-- User 테이블
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `user_id` varchar(50) NOT NULL,
                        `password` varchar(10) NOT NULL,
                        `nick_name` varchar(45) NOT NULL,
                        `name` varchar(50) NOT NULL,
                        `phone_number` varchar(20) NOT NULL,
                        `email` varchar(100) NOT NULL,
                        `registered_at` datetime NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci