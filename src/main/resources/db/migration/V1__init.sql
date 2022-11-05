CREATE TABLE `user_role` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY(`id`)
);

CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(100),
    `email` VARCHAR(45) UNIQUE,
    `phone_number` VARCHAR(20) UNIQUE,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`role_id`) REFERENCES `user_role`(`id`)
);

CREATE TABLE `login` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(45) NOT NULL UNIQUE,
    `password` VARCHAR(45) NOT NULL,
    `user_id` BIGINT NOT NULL UNIQUE,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `bank_account` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `iban` VARCHAR(28) NOT NULL UNIQUE,
    `status` VARCHAR(3) NOT NULL,
    `balance` DECIMAL(10,2) NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
);

CREATE TABLE `credit_card` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `number` BIGINT UNSIGNED NOT NULL UNIQUE,
    `name` VARCHAR(45),
    `type` VARCHAR(3) NOT NULL,
    `status` VARCHAR(3) NOT NULL,
    `validity` DATE NOT NULL,
    `bank_account_id` BIGINT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`bank_account_id`) REFERENCES `bank_account`(`id`)
);

CREATE TABLE `payment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `sender` BIGINT,
    `recipient` BIGINT NOT NULL,
    `date` TIMESTAMP NOT NULL,
    `name` VARCHAR(45),
    `amount` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`sender`) REFERENCES `bank_account`(`id`),
    FOREIGN KEY(`recipient`) REFERENCES `bank_account`(`id`)
);