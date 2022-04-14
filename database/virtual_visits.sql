-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: ip_project
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `action` varchar(45) NOT NULL,
  `info` varchar(90) NOT NULL,
  `date_time` datetime NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=511 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (423,'ATTEND-FAIL','Attempt to attend virtual visit failed for user:jelena123456','2022-04-09 16:04:52','Admin1234567'),(424,'ATTEND-FAIL','Attempt to attend virtual visit failed for user:jelena123456','2022-04-09 16:05:05','Admin1234567'),(425,'LOGOUT','jelena123456 logged out.','2022-04-09 16:05:42','Admin1234567'),(426,'LOGIN','Admin1234567 logged in.','2022-04-09 16:05:52','Admin1234567'),(427,'VISIT-ADD','Admin1234567 added visit with id 53','2022-04-09 16:13:06','Admin1234567'),(428,'LOGIN ADMIN APP','Admin Admin1234567 logged into admin JSP app.','2022-04-09 14:13:11','Admin1234567'),(429,'LOGOUT ADMIN APP','Admin Admin1234567 logged out from admin JSP app.','2022-04-09 14:13:14','Admin1234567'),(430,'LOGOUT','Admin1234567 logged out.','2022-04-09 16:13:16','Admin1234567'),(431,'LOGIN','jelena123456 logged in.','2022-04-09 16:13:28','Admin1234567'),(432,'BUY-TICKET-TRY','Buy ticket try for user id:3 and visit 53','2022-04-09 16:14:05','Admin1234567'),(433,'TRANSACTION-FAILED','Transaction failed for user:jelena123456 buying ticket for virtual visit:53','2022-04-09 16:14:06','Admin1234567'),(434,'BUY-TICKET-TRY','Buy ticket try for user id:3 and visit 53','2022-04-09 16:14:25','Admin1234567'),(435,'BUY-TICKET','jelena123456 bought ticket for visit 53 . Ticket number:1649513664871','2022-04-09 16:14:27','Admin1234567'),(436,'LOGOUT','jelena123456 logged out.','2022-04-09 16:22:54','Admin1234567'),(437,'LOGIN','jelena123456 logged in.','2022-04-09 16:23:33','Admin1234567'),(438,'ATTEND-VISIT','Sending url-s for visit 53 to user:jelena123456','2022-04-09 16:23:52','Admin1234567'),(439,'LOGOUT','jelena123456 logged out.','2022-04-09 16:24:13','Admin1234567'),(440,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:01:25','Admin1234567'),(441,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:04:31','Admin1234567'),(442,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:06:53','Admin1234567'),(443,'PASSWORD-RESET','Admin1234567 password reset for user with id:11','2022-04-12 19:06:56','Admin1234567'),(444,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:10:34','Admin1234567'),(445,'USER-UPDATE','Admin1234567 updated user with username:nalog12345678','2022-04-12 19:10:43','Admin1234567'),(446,'PASSWORD-RESET','Admin1234567 password reset for user with id:11','2022-04-12 19:10:46','Admin1234567'),(447,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:14:19','Admin1234567'),(448,'PASSWORD-RESET','Admin1234567 password reset for user with id:11','2022-04-12 19:14:25','Admin1234567'),(449,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:15:49','Admin1234567'),(450,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:17:12','Admin1234567'),(451,'PASSWORD-RESET','Admin1234567 password reset for user with id:11','2022-04-12 19:17:49','Admin1234567'),(452,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:21:24','Admin1234567'),(453,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-12 19:21:26','Admin1234567'),(454,'USER-ADD','Admin1234567 added user with username:aaaaaaaaaaaaaaaaa','2022-04-12 19:22:25','Admin1234567'),(455,'USER-DELETE','Admin1234567 deleted user with id:14','2022-04-12 19:22:33','Admin1234567'),(456,'LOGOUT ADMIN APP','Admin Admin1234567 logged out from admin JSP app.','2022-04-12 19:22:36','Admin1234567'),(457,'LOGIN','Admin1234567 logged in.','2022-04-12 21:29:18','Admin1234567'),(458,'LOGIN','Admin1234567 logged in.','2022-04-12 21:35:18','Admin1234567'),(459,'LOGIN','Admin1234567 logged in.','2022-04-12 21:29:18','Admin12345678'),(460,'LOGIN','Admin1234567 logged in.','2022-04-13 00:17:11','Admin1234567'),(461,'LOGIN','Admin1234567 logged in.','2022-04-13 23:17:11','Admin1234567'),(462,'LOGIN','Admin1234567 logged in.','2022-04-13 22:17:11','Admin1234567'),(463,'LOGIN','Admin1234567 logged in.','2022-04-13 22:17:11','Admin12345678'),(464,'LOGIN','Admin1234567 logged in.','2022-04-13 00:31:49','Admin1234567'),(465,'LOGIN','Admin1234567 logged in.','2022-04-13 00:58:19','Admin1234567'),(466,'LOGIN','Admin1234567 logged in.','2022-04-13 12:59:30','Admin1234567'),(467,'MUSEUM-ADD','Admin1234567 added new museum with id 22','2022-04-13 13:05:43','Admin1234567'),(468,'MUSEUM-UPDATE','Admin1234567 updated museum with id 22','2022-04-13 13:08:02','Admin1234567'),(469,'TOKEN-VALIDATION-FAIL','Jwt token validation failed.','2022-04-13 13:09:53',NULL),(470,'TOKEN-VALIDATION-FAIL','Jwt token validation failed.','2022-04-13 13:10:12',NULL),(471,'LOGIN','Admin1234567 logged in.','2022-04-13 13:15:10','Admin1234567'),(472,'LOGIN','Admin1234567 logged in.','2022-04-13 13:15:34','Admin1234567'),(473,'TOKEN-VALIDATION-FAIL','Jwt token validation failed.','2022-04-13 13:16:14',NULL),(474,'MUSEUM-DELETE','Admin1234567 deleted museum with id 22','2022-04-13 13:16:22','Admin1234567'),(475,'LOGIN','Admin1234567 logged in.','2022-04-13 13:51:41','Admin1234567'),(476,'VISIT-DELETE','Admin1234567 deleted visit with id 52','2022-04-13 14:03:06','Admin1234567'),(477,'LOGIN','Admin1234567 logged in.','2022-04-13 14:12:54','Admin1234567'),(478,'TOKEN-VALIDATION-FAIL','Jwt token validation failed.','2022-04-13 14:20:00',NULL),(479,'LOGIN','Admin1234567 logged in.','2022-04-13 14:20:05','Admin1234567'),(480,'VISIT-ADD','Admin1234567 added visit with id 54','2022-04-13 14:20:16','Admin1234567'),(481,'VISIT-ADD','Admin1234567 added visit with id 55','2022-04-13 14:22:51','Admin1234567'),(482,'VISIT-DELETE','Admin1234567 deleted visit with id 55','2022-04-13 14:25:44','Admin1234567'),(483,'VISIT-UPDATE','Admin1234567 updated visit with id 54','2022-04-13 14:33:03','Admin1234567'),(484,'SIGN-UP','Sign up request for username: Test12365478912','2022-04-14 11:20:55',NULL),(485,'LOGIN','Admin1234567 logged in.','2022-04-14 11:21:10','Admin1234567'),(486,'LOGIN','jelena123456 logged in.','2022-04-14 11:26:07','jelena123456'),(487,'VISIT-ADD','Admin1234567 added visit with id 56','2022-04-14 11:28:44','Admin1234567'),(488,'VISIT-UPDATE','Admin1234567 updated visit with id 54','2022-04-14 11:29:22','Admin1234567'),(489,'VISIT-DELETE','Admin1234567 deleted visit with id 54','2022-04-14 11:29:25','Admin1234567'),(490,'VISIT-UPDATE','Admin1234567 updated visit with id 56','2022-04-14 11:30:35','Admin1234567'),(491,'BUY-TICKET-TRY','Buy ticket try for user id:3 and visit 56','2022-04-14 11:31:31','jelena123456'),(492,'BUY-TICKET','jelena123456 bought ticket for visit 56 . Ticket number:1649928691994','2022-04-14 11:31:43','jelena123456'),(493,'LOGOUT','jelena123456 logged out.','2022-04-14 11:34:50','jelena123456'),(494,'LOGIN','jelena123456 logged in.','2022-04-14 11:35:26','jelena123456'),(495,'ATTEND-VISIT','Sending url-s for visit 56 to user:jelena123456','2022-04-14 11:36:01','jelena123456'),(496,'TOKEN-VALIDATION-FAIL','Jwt token validation failed.','2022-04-14 11:36:19',NULL),(497,'TOKEN-VALIDATION-FAIL','Jwt token validation failed.','2022-04-14 11:36:19',NULL),(498,'REFRESH-TOKEN','Admin1234567 refreshed JWT.','2022-04-14 11:36:19','Admin1234567'),(499,'LOGOUT','Admin1234567 logged out.','2022-04-14 11:36:19','Admin1234567'),(500,'LOGIN','Admin1234567 logged in.','2022-04-14 11:38:33','Admin1234567'),(501,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-14 09:38:35','Admin1234567'),(502,'LOGIN ADMIN APP FAIL','Bad login with token:1649929112889','2022-04-14 09:39:37',NULL),(503,'LOGOUT','Admin1234567 logged out.','2022-04-14 11:39:52','Admin1234567'),(504,'LOGIN','Admin1234567 logged in.','2022-04-14 11:40:02','Admin1234567'),(505,'LOGIN-ADMIN-APP','Admin Admin1234567 logged into admin JSP app.','2022-04-14 09:40:44','Admin1234567'),(506,'USER-UPDATE','Admin1234567 updated user with username:Test12365478912','2022-04-14 09:40:54','Admin1234567'),(507,'USER-ADD','Admin1234567 added user with username:uketuk68l7;9;7ry','2022-04-14 09:41:34','Admin1234567'),(508,'USER-DELETE','Admin1234567 deleted user with id:16','2022-04-14 09:41:38','Admin1234567'),(509,'LOGOUT ADMIN APP','Admin Admin1234567 logged out from admin JSP app.','2022-04-14 09:41:42','Admin1234567'),(510,'LOGOUT','Admin1234567 logged out.','2022-04-14 11:42:07','Admin1234567');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `museum`
--

DROP TABLE IF EXISTS `museum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `museum` (
  `museum_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(65) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `latitude` decimal(11,8) NOT NULL,
  PRIMARY KEY (`museum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `museum`
--

LOCK TABLES `museum` WRITE;
/*!40000 ALTER TABLE `museum` DISABLE KEYS */;
INSERT INTO `museum` VALUES (1,'Louvre Museum','Rue de Rivoli','+33 1 40 20 50 50','Paris','France','Art and Antiques',2.33759880,48.86064900),(2,'National Museum','Václavské nám. 68','+420 224 497 111','Prague','Czech Republic','History ',14.43090000,50.07890000),(3,'Acropolis Museum','Dionysiou Areopagitou 15','+30 21 0900 0900','Athens','Greece','Archeological',23.72611000,37.97083300),(4,'Art History Museum','Maria-Theresien-Platz','+43 1 525240','Vienna','Austria','Art',16.36140000,48.20370000),(5,'The Prado','C. de Ruiz de Alarcón, 23','+34 913 30 28 00','Madrid','Spain','Art',-3.69210000,40.41380000),(6,'The Museum Of The Second World War','plac Władysława Bartoszewskiego 1','+48 58 760 09 60','Gdansk','Poland','History',18.66000000,54.35600000),(7,'Viking Ship Museum',' Huk Aveny 35','+47 22 13 52 80','Oslo','Norway','History',10.68440000,59.90490000),(8,'Orsay Museum','1 Rue de la Légion d\'Honneur','+33 1 40 49 48 14','Paris','France','Art',2.32660000,48.86000000),(9,'The British Museum',' Great Russell St','+44 20 7323 8299','London','United Kingdom','Art and History',0.12700000,51.51940000),(10,'Anne Frank House','Westermarkt 20, 1016 GV','+31 20 556 7105','Amsterdam','Netherlands','Biographical',4.88400000,52.37520000),(13,'Rijksmuseum','Museumstraat 1, 1071 XX',' +31 20 674 7000','Amsterdam','Netherlands','Art and History',4.88520000,52.36000000),(14,'Borghese Gallery','Piazzale Scipione Borghese, 5',' +39 06 841 3979','Roma','Italy','Art gallery',12.49206000,41.91412000);
/*!40000 ALTER TABLE `museum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `virtual_visit_id` int NOT NULL,
  `ticket_number` varchar(20) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `fk_ticket_user1_idx` (`user_id`),
  KEY `fk_ticket_virtual_visit1_idx` (`virtual_visit_id`),
  CONSTRAINT `fk_ticket_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_ticket_virtual_visit1` FOREIGN KEY (`virtual_visit_id`) REFERENCES `virtual_visit` (`virtual_visit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (7,1,7,'1647263426566'),(8,1,1,'1647263494890'),(11,1,8,'1647732920744'),(12,1,8,'1648200239648'),(13,3,51,'1649414519238'),(14,3,53,'1649513664871'),(15,3,56,'1649928691994');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(150) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `role` tinyint NOT NULL,
  `status` tinyint NOT NULL,
  `otp_token` varchar(150) DEFAULT NULL,
  `is_logged_in` tinyint DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','Test','Admin1234567','edfff33654ea3dbd47b0f81addd53ffc6d669ec9d32adabbb5c1f10cd7104e8d','jelena.pesevski@student.etf.unibl.org',1,1,NULL,0),(3,'Jelena','Pesevski','jelena123456','3fc351a5e33466b81c50703bf39b3bbb74510b1e776f48e76418d2ecf3f94396','jpesevski99@gmail.com',0,1,'1649928925672',1),(7,'test','test','test12345678','22d3d86676542f72cd3fc0ebabf0150122bcc74772681a1ab2df02cc58a2bbde','jpesevski99@gmail.com',0,1,NULL,0),(11,'nalog1','nalog1','nalog12345678','976df427b7d3874fe71aa13652d1f5b62cc3dec967d9bba884255ad0ca7afa2f','jpesevski99@gmail.com',0,1,NULL,0),(15,'trest','test','Test12365478912','2babced2fb8d8b0163cec0a2d8e294068288fea24d1f944cf87253a67a3d1da9','test@mail.com',0,1,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virtual_visit`
--

DROP TABLE IF EXISTS `virtual_visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `virtual_visit` (
  `virtual_visit_id` int NOT NULL AUTO_INCREMENT,
  `museum_id` int NOT NULL,
  `date` date NOT NULL,
  `start` time NOT NULL,
  `duration` time NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `folder` varchar(45) NOT NULL,
  `yt_link` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`virtual_visit_id`),
  KEY `fk_virtual_visit_museum_idx` (`museum_id`),
  CONSTRAINT `fk_virtual_visit_museum` FOREIGN KEY (`museum_id`) REFERENCES `museum` (`museum_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virtual_visit`
--

LOCK TABLES `virtual_visit` WRITE;
/*!40000 ALTER TABLE `virtual_visit` DISABLE KEYS */;
INSERT INTO `virtual_visit` VALUES (1,1,'2023-04-20','18:00:00','03:00:00',50.00,'test',NULL),(2,2,'2020-03-20','17:00:00','01:00:00',50.00,'test',NULL),(3,2,'2022-03-06','22:00:00','01:00:00',50.00,'test',NULL),(4,1,'2022-03-06','11:00:00','02:00:00',50.00,'test',NULL),(6,2,'2022-03-12','13:00:00','01:30:00',50.00,'test',NULL),(7,1,'2022-04-01','16:00:00','02:00:00',50.00,'test',NULL),(8,1,'2022-03-25','10:00:00','05:05:00',25.00,'8','https://www.youtube.com/embed/zp1BXPX8jcU'),(9,2,'2022-04-13','12:00:00','03:00:00',25.00,'9','https://www.youtube.com/embed/6vuFh6NNa70'),(40,4,'2001-02-09','01:00:00','01:00:00',25.00,'40',NULL),(51,5,'2022-04-08','12:43:00','00:12:00',25.00,'51',NULL),(53,3,'2022-04-19','16:20:00','01:00:00',15.00,'53',NULL),(56,4,'2022-04-14','11:34:00','01:00:00',25.00,'56',NULL);
/*!40000 ALTER TABLE `virtual_visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-14 12:28:09
