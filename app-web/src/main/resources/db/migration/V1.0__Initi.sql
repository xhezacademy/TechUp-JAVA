CREATE TABLE `articles`
(
    `id`        bigint(20)   NOT NULL AUTO_INCREMENT,
    `title`     varchar(255) NOT NULL,
    `slug`      varchar(255) NOT NULL,
    `body`      text       DEFAULT NULL,
    `published` tinyint(1) DEFAULT 0,
    `createdAt` datetime   DEFAULT current_timestamp(),
    `updatedAt` datetime   DEFAULT current_timestamp(),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `articles`(`title`, `slug`, `body`, `published`)
    VALUES ('This is my first blog article', 'my-first-blog-article', 'blah blah blah here I come', true),
        ('Shpallen rezultatet perfundimtare te zgjedhjeve', 'shpallen-rezultatet-perfundimtare', 'LVV Winner', true)
    ;