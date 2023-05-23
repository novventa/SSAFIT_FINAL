CREATE DATABASE ssafit;

USE ssafit;

CREATE TABLE `user`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `id` VARCHAR(40) NOT NULL UNIQUE,
    `password` VARCHAR(40) NOT NULL,
    `nickname` VARCHAR(20) NOT NULL UNIQUE,
    `email` VARCHAR(40) NOT NULL UNIQUE,
    `image` VARCHAR(100) DEFAULT NULL
);

CREATE TABLE `video`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `videoId` VARCHAR(40) NOT NULL UNIQUE,
    `title` VARCHAR(50) NOT NULL,
    `channelName` VARCHAR(40) NOT NULL,
    `part` VARCHAR(20) NOT NULL,
    `viewCnt` INT DEFAULT 0,
    `likeCnt` INT DEFAULT 0
);

CREATE TABLE `review`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `videoIdx` INT NOT NULL,
    `content` TEXT NOT NULL,
    `userIdx` INT NOT NULL,
    `writer` VARCHAR(20) NOT NULL,
    `parent` INT DEFAULT 0,
    `regDate` DATETIME NOT NULL DEFAULT now(),
    `modDate` DATETIME NOT NULL DEFAULT now(),
    FOREIGN KEY(`videoIdx`) REFERENCES `video`(`idx`) ON DELETE CASCADE,
    FOREIGN KEY(`writer`) REFERENCES `user`(`nickname`) ON DELETE CASCADE,
    FOREIGN KEY(`userIdx`) REFERENCES `user`(`idx`) ON DELETE CASCADE
);

-- 유저, 비디오, 리뷰, 캘린더, 찜, 팔로워,

CREATE TABLE `likes`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `videoIdx` INT NOT NULL,
    `userIdx` INT NOT NULL,
    FOREIGN KEY(`videoIdx`) REFERENCES `video`(`idx`) ON DELETE CASCADE,
    FOREIGN KEY(`userIdx`) REFERENCES `user`(`idx`) ON DELETE CASCADE
);

CREATE TABLE `follow`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `myIdx` INT NOT NULL,
    `yourIdx` INT NOT NULL,
    FOREIGN KEY(`yourIdx`) REFERENCES `user`(`idx`) ON DELETE CASCADE,
    FOREIGN KEY(`myIdx`) REFERENCES `user`(`idx`) ON DELETE CASCADE
);

CREATE TABLE `calendar`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `userIdx` INT NOT NULL,
    `date` DATE NOT NULL,
    `content` TEXT NOT NULL,
    `done` BOOLEAN DEFAULT FALSE,
    FOREIGN KEY(`userIdx`) REFERENCES `user`(`idx`) ON DELETE CASCADE
);