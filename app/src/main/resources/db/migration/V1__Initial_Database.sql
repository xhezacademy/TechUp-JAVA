CREATE TABLE IF NOT EXISTS `students`
(
    `id`        INT(11)      NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(50)  NOT NULL,
    `lastName`  VARCHAR(100) NOT NULL,
    `email`     VARCHAR(255) NOT NULL,
    `createdAt` DATETIME DEFAULT current_timestamp(),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `student_profile`
(
    `id`        INT(11)      NOT NULL AUTO_INCREMENT,
    `student_id` INT(50)  NOT NULL,
    `phoneNumber`  VARCHAR(100) NOT NULL,
    `alternateEmail` VARCHAR(255),
    `birthday` DATE,
    `biography` TEXT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `courses`(
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `description` TEXT,
    `startDate` DATE,
    `endDate` DATE,
    `createdAt` DATETIME DEFAULT current_timestamp(),
    PRIMARY KEY (`id`)
) ENGINE =InnoDB DEFAULT CHARSET = utf8
