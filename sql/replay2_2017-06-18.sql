# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 117.17.102.106 (MySQL 5.5.5-10.1.23-MariaDB)
# Database: replay2
# Generation Time: 2017-06-18 06:13:51 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_no` int(11) unsigned NOT NULL,
  `content_no` int(11) unsigned NOT NULL,
  `text` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `regist_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`no`),
  KEY `user_no` (`user_no`),
  KEY `content_no` (`content_no`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`no`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`content_no`) REFERENCES `content` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;

INSERT INTO `comment` (`no`, `user_no`, `content_no`, `text`, `regist_date`)
VALUES
	(1,1,1,'코멘트','2017-06-09 15:19:54'),
	(2,4,1,'코멘트2','2017-06-09 15:20:07'),
	(3,1,4,'코멘트4','2017-06-10 14:02:05'),
	(4,11,1,'111','2017-06-14 10:26:34'),
	(5,11,23,'456456','2017-06-14 10:28:03'),
	(6,13,1,'asasas','2017-06-14 13:12:57'),
	(7,13,2,'zxzxzx','2017-06-14 13:13:02'),
	(8,13,2,'123123','2017-06-14 13:20:18'),
	(9,14,23,'14141414','2017-06-14 13:21:49'),
	(10,20,31,'훙흉답글','2017-06-14 15:24:45'),
	(11,12,35,'ㅀㅀ','2017-06-15 13:50:42'),
	(12,12,35,'444','2017-06-15 13:51:47'),
	(19,12,36,'66666','2017-06-15 14:24:03'),
	(20,12,36,'8888','2017-06-15 14:27:54'),
	(21,12,36,'9999999999','2017-06-15 14:29:11'),
	(22,12,33,'111111','2017-06-15 14:29:18'),
	(23,12,37,'5555','2017-06-15 14:33:42'),
	(24,12,27,'55','2017-06-15 14:35:53'),
	(25,12,38,'dddd','2017-06-16 13:20:36'),
	(27,13,38,'와우','2017-06-16 15:25:24'),
	(28,15,40,'그러네','2017-06-16 15:44:04'),
	(29,22,41,'신난다','2017-06-16 16:07:33'),
	(30,23,42,' dkansk','2017-06-16 16:24:30'),
	(32,22,37,'sdsdsd','2017-06-16 16:27:10'),
	(33,24,44,'ddd','2017-06-16 16:28:54'),
	(34,24,23,'xxxx','2017-06-16 16:29:57'),
	(35,24,28,'zzzzz','2017-06-16 16:30:09'),
	(36,24,47,'asdasdasd','2017-06-16 17:05:26'),
	(37,23,48,'ㅎㅇㅎㅇ','2017-06-18 15:03:25');

/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table content
# ------------------------------------------------------------

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_no` int(11) unsigned NOT NULL,
  `contents` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `like` int(11) unsigned DEFAULT '0',
  `dislike` int(11) unsigned DEFAULT '0',
  `regist_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `wall` int(11) unsigned NOT NULL,
  `contentcol` int(11) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `user_no` (`user_no`),
  KEY `content_ibfk_2_idx` (`wall`),
  CONSTRAINT `content_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`no`),
  CONSTRAINT `content_ibfk_2` FOREIGN KEY (`wall`) REFERENCES `user` (`no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;

INSERT INTO `content` (`no`, `user_no`, `contents`, `like`, `dislike`, `regist_date`, `wall`, `contentcol`)
VALUES
	(1,1,'임시1',1,2,'2017-06-09 14:44:50',1,NULL),
	(2,1,'임시2',34,2,'2017-06-09 14:45:00',1,NULL),
	(3,4,'임시3',3,4,'2017-06-09 14:45:47',4,NULL),
	(4,1,'임시4',34,2,'2017-06-09 14:45:58',1,NULL),
	(5,5,'임시5',34,6,'2017-06-13 17:38:05',5,NULL),
	(6,7,'임시6',2,4,'2017-06-13 17:40:23',7,NULL),
	(7,6,'임시7',55,5,'2017-06-13 17:45:15',6,NULL),
	(10,8,'임시8',0,0,'2017-06-14 00:29:48',8,NULL),
	(17,11,'asd',0,0,'2017-06-14 01:10:01',11,NULL),
	(18,8,'asd',0,0,'2017-06-14 01:10:15',11,NULL),
	(19,11,'dfd',0,0,'2017-06-14 01:13:34',11,NULL),
	(20,7,'ddddd',0,0,'2017-06-14 01:15:30',11,NULL),
	(21,1,'asdxczxcqw',0,0,'2017-06-14 09:23:00',1,NULL),
	(22,1,'fdgsfgsdfg',0,0,'2017-06-14 09:23:18',1,NULL),
	(23,11,'123123',0,0,'2017-06-14 09:44:34',11,NULL),
	(24,11,'qweqe',0,0,'2017-06-14 09:46:53',11,NULL),
	(25,1,'asdad',0,0,'2017-06-14 09:52:23',11,NULL),
	(26,11,'adasdqq',0,0,'2017-06-14 09:53:54',11,NULL),
	(27,4,'asdasd',0,0,'2017-06-14 09:55:35',11,NULL),
	(28,11,'adsad',0,0,'2017-06-14 10:01:04',11,NULL),
	(29,14,'asdasdasd',0,0,'2017-06-14 13:53:45',14,NULL),
	(30,14,'ㄴㅇㄴㅇㄴㅇ',0,0,'2017-06-14 14:40:33',1,NULL),
	(31,20,'휑휑',0,0,'2017-06-14 15:24:34',20,NULL),
	(32,20,'sdfsfsdf',0,0,'2017-06-14 22:36:16',1,NULL),
	(33,12,'adsasd',0,0,'2017-06-15 10:53:19',12,NULL),
	(34,12,'ㅁㄴㄻㄴㄻㄹ',0,0,'2017-06-15 11:48:56',20,NULL),
	(35,11,'???\r\n',0,0,'2017-06-15 13:32:49',11,NULL),
	(36,12,'ㄴㅇㄹㄴ',0,0,'2017-06-15 13:57:43',11,NULL),
	(37,12,'ㄴㅇㄹㄴㅇㄹㄴ',0,0,'2017-06-15 14:33:38',12,NULL),
	(38,12,'asdasdasd',0,0,'2017-06-16 13:20:29',12,NULL),
	(39,13,'촤하하하하하\r\n',0,0,'2017-06-16 15:25:12',13,NULL),
	(40,20,'친구네',0,0,'2017-06-16 15:34:25',13,NULL),
	(41,15,'뭐지뭐지\r\n',0,0,'2017-06-16 15:42:51',15,NULL),
	(42,22,'시험용',0,0,'2017-06-16 16:08:00',22,NULL),
	(43,23,'daadad',0,0,'2017-06-16 16:24:43',23,NULL),
	(44,22,'dkdkdkd',0,0,'2017-06-16 16:26:47',23,NULL),
	(45,24,'xxxx',0,0,'2017-06-16 16:29:16',23,NULL),
	(46,24,'aaa',0,0,'2017-06-16 16:31:23',24,NULL),
	(47,24,'dddd',0,0,'2017-06-16 16:41:10',23,NULL),
	(48,27,'가입인사',0,0,'2017-06-18 15:02:29',27,NULL),
	(49,23,'안녕하세요',0,0,'2017-06-18 15:03:30',27,NULL);

/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table friend
# ------------------------------------------------------------

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `friend_no` int(11) unsigned NOT NULL,
  `friend2_no` int(11) unsigned NOT NULL,
  `relation` enum('full','half') COLLATE utf8mb4_unicode_ci DEFAULT 'half',
  PRIMARY KEY (`no`),
  KEY `friend_no` (`friend_no`),
  KEY `friend2_no` (`friend2_no`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`friend_no`) REFERENCES `user` (`no`),
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`friend2_no`) REFERENCES `user` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;

INSERT INTO `friend` (`no`, `friend_no`, `friend2_no`, `relation`)
VALUES
	(1,11,1,'full'),
	(2,1,5,'half'),
	(17,7,1,'full'),
	(19,1,6,'half'),
	(20,13,20,'full'),
	(21,13,11,'half'),
	(22,23,22,'full'),
	(23,27,23,'full');

/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `no` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `password` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `name` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `regist_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `photo` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '/photoes/anonymous.png',
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`no`, `email`, `password`, `name`, `regist_date`, `photo`)
VALUES
	(1,'qwer@never.com','1234','이름1','2017-06-09 14:32:26','/photos/anonymous.png'),
	(4,NULL,'1111','이름4','2017-06-09 14:35:23','/photos/anonymous.png'),
	(5,'asdf@naver.com','123','이름5','2017-06-09 14:45:26','/photos/anonymous.png'),
	(6,'a@daum.net','123','카카6','2017-06-13 00:00:57','/photos/anonymous.png'),
	(7,'b@hanmail.com','123','다음7','2017-06-13 00:01:16','/photos/anonymous.png'),
	(8,'c@wow.com','123','와우8','2017-06-13 18:17:25','/photos/anonymous.png'),
	(10,'erer@coc.com','123','뚱이','2017-06-13 19:36:28','/photos/anonymous.png'),
	(11,'1','1','master','2017-06-13 19:48:55','/photos/anonymous.png'),
	(12,'2','2','모자','2017-06-14 00:10:56','/photos/KakaoTalk_Photo_2016-10-24-01-26-00.jpeg'),
	(13,'123123','123123','유해진','2017-06-14 11:54:57','/photos/anonymous.png'),
	(14,'121212','1','김재현','2017-06-14 12:30:57','/photos/anonymous.png'),
	(15,'000','000','징징이','2017-06-14 14:05:48','/photos/124.png'),
	(16,'55555','55','철수','2017-06-14 14:11:11','/photos/anonymous.png'),
	(17,'7878','7878','영희','2017-06-14 14:13:48','/photos/anonymous.png'),
	(18,'tt','ttt','제주대','2017-06-14 14:15:48','/photos/anonymous.png'),
	(19,'rr','1','스퍼지','2017-06-14 14:16:44','/photos/헤더.JPG'),
	(20,'qq','1','별가사리','2017-06-14 15:24:06','/photos/anonymous.png'),
	(21,'ㄳㄳ','rtrt','유재석','2017-06-15 11:21:46','/photos/anonymous.png'),
	(22,'aaa','aaa','김우찬','2017-06-16 16:06:56','/photos/anonymous.png'),
	(23,'erer','erer','김재현','2017-06-16 16:24:12','/photos/image.png'),
	(24,'aaaa','aaaa','aaaa','2017-06-16 16:27:58','/photos/124.png'),
	(27,'wtwt','wtwt','장은아','2017-06-18 15:02:07','/photos/스크린샷 2017-05-19 오후 9.58.33.png');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
