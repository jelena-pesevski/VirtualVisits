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
  `username` varchar(45) NOT NULL,
  `action` varchar(45) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `museum`
--

LOCK TABLES `museum` WRITE;
/*!40000 ALTER TABLE `museum` DISABLE KEYS */;
INSERT INTO `museum` VALUES (1,'Louvre Museum','Rue de Rivoli','+33 1 40 20 50 50','Paris','France','Art and Antiques',2.33862900,48.86029400),(2,'National Museum','Václavské nám. 68','+420 224 497 111','Prague','Czech Republic','History ',14.43090000,50.07890000),(3,'Acropolis Museum','Dionysiou Areopagitou 15','+30 21 0900 0900','Athens','Greece','Archeological',23.72611000,37.97083300),(4,'Art History Museum','Maria-Theresien-Platz','+43 1 525240','Vienna','Austria','Art',16.36140000,48.20370000),(5,'The Prado','C. de Ruiz de Alarcón, 23','+34 913 30 28 00','Madrid','Spain','Art',-3.69210000,40.41380000),(6,'The Museum Of The Second World War','plac Władysława Bartoszewskiego 1','+48 58 760 09 60','Gdansk','Poland','History',18.66000000,54.35600000),(7,'Viking Ship Museum',' Huk Aveny 35','+47 22 13 52 80','Oslo','Norway','History',10.68440000,59.90490000),(8,'Orsay Museum','1 Rue de la Légion d\'Honneur','+33 1 40 49 48 14','Paris','France','Art',2.32660000,48.86000000),(9,'The British Museum',' Great Russell St','+44 20 7323 8299','London','United Kingdom','Art and History',0.12700000,51.51940000),(10,'Anne Frank House','Westermarkt 20, 1016 GV','+31 20 556 7105','Amsterdam','Netherlands','Biographical',4.88400000,52.37520000),(12,'National Museum Of Scotland','Chambers St','+44 300 123 6789','Edinburgh','Scotland','National',-3.19060000,55.94700000),(13,'Rijksmuseum','Museumstraat 1, 1071 XX',' +31 20 674 7000','Amsterdam','Netherlands','Art and History',4.88520000,52.36000000);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (7,1,7,'1647263426566'),(8,1,1,'1647263494890'),(11,1,8,'1647732920744');
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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','Test','Admin1234567','edfff33654ea3dbd47b0f81addd53ffc6d669ec9d32adabbb5c1f10cd7104e8d','jelena.pesevski@student.etf.unibl.org',1,1,'1647794021812'),(2,'Test','Test','Test12345678','022731e8df52163b49560c77678583a65938caa0fb6d67a34ac8aabdb14ec230','test@mail.com',0,1,'1646824973106'),(3,'Jelena','Pesevski','jelena123456','3fc351a5e33466b81c50703bf39b3bbb74510b1e776f48e76418d2ecf3f94396','jelena.pesevski@student.etf.unibl.org',0,1,'1647646576142');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virtual_visit`
--

LOCK TABLES `virtual_visit` WRITE;
/*!40000 ALTER TABLE `virtual_visit` DISABLE KEYS */;
INSERT INTO `virtual_visit` VALUES (1,1,'2023-04-20','18:00:00','03:00:00',50.00,'test',NULL),(2,2,'2020-03-20','17:00:00','01:00:00',50.00,'test',NULL),(3,2,'2022-03-06','22:00:00','01:00:00',50.00,'test',NULL),(4,1,'2022-03-06','11:00:00','02:00:00',50.00,'test',NULL),(5,1,'2022-03-11','17:30:00','04:00:00',50.00,'test',NULL),(6,2,'2022-03-12','13:00:00','01:30:00',50.00,'test',NULL),(7,1,'2022-04-01','16:00:00','02:00:00',50.00,'test',NULL),(8,1,'2022-03-20','14:00:00','05:05:00',25.00,'8','https://www.youtube.com/embed/zp1BXPX8jcU');
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

-- Dump completed on 2022-03-20 17:43:32
