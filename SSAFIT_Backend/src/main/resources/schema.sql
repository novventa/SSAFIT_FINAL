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
    `part` VARCHAR(20) NOT NULL,
    `viewCnt` INT DEFAULT 0,
    `likeCnt` INT DEFAULT 0
);

CREATE TABLE `review`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `videoId` VARCHAR(40) NOT NULL,
    `content` TEXT NOT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    `parent` INT NOT NULL,
    `regDate` DATETIME NOT NULL DEFAULT now(),
    `modDate` DATETIME NOT NULL DEFAULT now(),
    FOREIGN KEY(`videoId`) REFERENCES `video`(`videoId`),
    FOREIGN KEY(`nickname`) REFERENCES `user`(`nickname`)
);

-- 유저, 비디오, 리뷰, 캘린더, 찜, 팔로워,

CREATE TABLE `dibs`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `videoId` VARCHAR(40) NOT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    FOREIGN KEY(`videoId`) REFERENCES `video`(`videoId`),
    FOREIGN KEY(`nickname`) REFERENCES `user`(`nickname`)
);

CREATE TABLE `follow`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `myNickname` VARCHAR(20) NOT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    FOREIGN KEY(`nickname`) REFERENCES `user`(`nickname`),
    FOREIGN KEY(`myNickname`) REFERENCES `user`(`nickname`)
);

CREATE TABLE `calendar`(
	`idx` INT AUTO_INCREMENT PRIMARY KEY,
    `nickname` VARCHAR(20) NOT NULL,
    `date` DATE NOT NULL,
    `content` TEXT NOT NULL,
    `done` BOOLEAN DEFAULT FALSE,
    FOREIGN KEY(`nickname`) REFERENCES `user`(`nickname`)
);