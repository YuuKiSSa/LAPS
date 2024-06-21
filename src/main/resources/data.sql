-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
-- --
-- Host: localhost    Database: laps
-- ------------------------------------------------------
-- Server version	8.4.0

-- /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
-- /*!50503 SET NAMES utf8 */;
-- /*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
-- /*!40103 SET TIME_ZONE='+00:00' */;
-- /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
-- /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
-- /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --
-- Table structure for table `admin`
-- --

-- DROP TABLE IF EXISTS `admin`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `admin` (
--   `user_id` varchar(20) NOT NULL,
--   `password` varchar(20) DEFAULT NULL,
--   PRIMARY KEY (`user_id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

-- --
-- Dumping data for table `admin`
-- --

-- LOCK TABLES `admin` WRITE;
-- /*!40000 ALTER TABLE `admin` DISABLE KEYS */;
-- INSERT INTO `admin` VALUES ('123456','123456');
-- /*!40000 ALTER TABLE `admin` ENABLE KEYS */;
-- UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 10:58:30


-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
-- --
-- Host: localhost    Database: laps
-- ------------------------------------------------------
-- Server version	8.4.0

-- /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
-- /*!50503 SET NAMES utf8 */;
-- /*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
-- /*!40103 SET TIME_ZONE='+00:00' */;
-- /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
-- /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
-- /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --
-- Table structure for table `application_type`
-- --

-- DROP TABLE IF EXISTS `application_type`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `application_type` (
--   `id` int NOT NULL AUTO_INCREMENT,
--   `type` varchar(20) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

-- --
-- Dumping data for table `application_type`
-- --

-- LOCK TABLES `application_type` WRITE;
-- /*!40000 ALTER TABLE `application_type` DISABLE KEYS */;
-- INSERT INTO `application_type` VALUES (1,'Medical Leave'),(2,'Compensation Leave'),(3,'Annual Leave'),(4,'Compensation');
-- /*!40000 ALTER TABLE `application_type` ENABLE KEYS */;
-- UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 10:58:30


-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
-- --
-- Host: localhost    Database: laps
-- ------------------------------------------------------
-- Server version	8.4.0

-- /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
-- /*!50503 SET NAMES utf8 */;
-- /*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
-- /*!40103 SET TIME_ZONE='+00:00' */;
-- /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
-- /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
-- /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --
-- Table structure for table `application`
-- --

-- DROP TABLE IF EXISTS `application`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `application` (
--   `id` bigint NOT NULL AUTO_INCREMENT,
--   `description` varchar(1000) DEFAULT NULL,
--   `end_time` datetime(6) DEFAULT NULL,
--   `start_time` datetime(6) DEFAULT NULL,
--   `status` varchar(10) DEFAULT NULL,
--   `type_id` int NOT NULL,
--   `user_id` varchar(20) NOT NULL,
--   PRIMARY KEY (`id`),
--   KEY `type_id` (`type_id`),
--   KEY `user_id` (`user_id`),
--   CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `application_type` (`id`),
--   CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `staff` (`user_id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

-- --
-- Dumping data for table `application`
-- --

-- LOCK TABLES `application` WRITE;
-- /*!40000 ALTER TABLE `application` DISABLE KEYS */;
-- INSERT INTO `application` VALUES (19,'','2024-07-30 17:00:00.000000','2024-06-20 09:00:00.000000','Updated',1,'111111'),(23,'','2024-09-24 17:00:00.000000','2024-09-13 09:00:00.000000','Updated',1,'111111'),(27,'','2025-06-30 17:00:00.000000','2025-06-01 09:00:00.000000','Applied',1,'111111'),(28,'','2024-10-02 16:00:00.000000','2024-10-01 12:00:00.000000','Cancel',4,'111111'),(29,'','2024-10-03 14:00:00.000000','2024-10-03 12:00:00.000000','Applied',4,'111111'),(31,'','2024-09-11 16:00:00.000000','2024-09-11 12:00:00.000000','Deleted',4,'111111'),(33,'','2024-09-07 17:00:00.000000','2024-09-07 13:00:00.000000','Updated',2,'111111'),(34,'','2024-09-05 13:00:00.000000','2024-09-05 09:00:00.000000','Updated',2,'111111'),(35,'','2024-09-07 13:00:00.000000','2024-09-07 09:00:00.000000','Applied',2,'111111'),(42,'','2024-11-01 15:00:00.000000','2024-11-01 12:00:00.000000','Rejected',4,'111111'),(45,'','2024-07-10 17:00:00.000000','2024-07-05 09:00:00.000000','Updated',3,'222222'),(46,'','2024-08-12 17:00:00.000000','2024-08-08 09:00:00.000000','Applied',3,'222222'),(47,'','2024-09-09 17:00:00.000000','2024-09-06 09:00:00.000000','Applied',3,'222222'),(55,'','2024-06-20 17:00:00.000000','2024-06-19 09:00:00.000000','Applied',3,'222222'),(57,'','2024-07-18 17:00:00.000000','2024-07-17 09:00:00.000000','Applied',3,'222222'),(67,'','2024-08-22 13:00:00.000000','2024-08-22 12:00:00.000000','Applied',4,'111111'),(1,'Annual leave','2024-07-10 17:00:00.000000','2024-07-01 09:00:00.000000','Approved',3,'111111'),
-- (2,'Medical leave','2024-08-15 17:00:00.000000','2024-08-05 09:00:00.000000','Approved',1,'222222'),
-- (3,'Personal leave','2024-09-20 17:00:00.000000','2024-09-10 09:00:00.000000','Rejected',3,'333333'),
-- (4,'Annual leave','2024-10-05 17:00:00.000000','2024-10-01 09:00:00.000000','Approved',3,'444444'),
-- (5,'Sick leave','2024-11-20 17:00:00.000000','2024-11-12 09:00:00.000000','Rejected',1,'555555'),
-- (6,'Compensation leave','2024-12-10 17:00:00.000000','2024-12-01 09:00:00.000000','Approved',2,'666666'),
-- (7,'Annual leave','2025-01-20 17:00:00.000000','2025-01-10 09:00:00.000000','Approved',3,'111111'),
-- (8,'Medical leave','2025-02-15 17:00:00.000000','2025-02-05 09:00:00.000000','Approved',1,'222222'),
-- (9,'Personal leave','2025-03-20 17:00:00.000000','2025-03-10 09:00:00.000000','Rejected',3,'333333'),
-- (10,'Annual leave','2025-04-05 17:00:00.000000','2025-04-01 09:00:00.000000','Approved',3,'444444'),
-- (11,'Compensation leave','2025-05-15 17:00:00.000000','2025-05-10 09:00:00.000000','Applied',2,'555555'),
-- (12,'Sick leave','2025-06-20 17:00:00.000000','2025-06-15 09:00:00.000000','Applied',1,'666666'),
-- (13,'Annual leave','2025-07-25 17:00:00.000000','2025-07-20 09:00:00.000000','Applied',3,'111111'),
-- (14,'Medical leave','2025-08-30 17:00:00.000000','2025-08-25 09:00:00.000000','Applied',1,'222222'),
-- (15,'Personal leave','2025-09-05 17:00:00.000000','2025-09-01 09:00:00.000000','Applied',3,'333333');
-- /*!40000 ALTER TABLE `application` ENABLE KEYS */;
-- UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 10:58:31


-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
-- --
-- Host: localhost    Database: laps
-- ------------------------------------------------------
-- Server version	8.4.0

-- /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
-- /*!50503 SET NAMES utf8 */;
-- /*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
-- /*!40103 SET TIME_ZONE='+00:00' */;
-- /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
-- /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
-- /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --
-- Table structure for table `department`
-- --

-- DROP TABLE IF EXISTS `department`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `department` (
--   `id` int NOT NULL AUTO_INCREMENT,
--   `annual_leave` int NOT NULL,
--   `name` varchar(20) DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE KEY `UK1t68827l97cwyxo9r1u6t4p7d` (`name`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

-- --
-- Dumping data for table `department`
-- --

-- LOCK TABLES `department` WRITE;
-- /*!40000 ALTER TABLE `department` DISABLE KEYS */;
-- INSERT INTO `department` VALUES (1,14,'D1'),(2,18,'D2'),(3,21,'D3');
-- /*!40000 ALTER TABLE `department` ENABLE KEYS */;
-- UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 10:58:31


-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
-- --
-- Host: localhost    Database: laps
-- ------------------------------------------------------
-- Server version	8.4.0

-- /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
-- /*!50503 SET NAMES utf8 */;
-- /*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
-- /*!40103 SET TIME_ZONE='+00:00' */;
-- /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
-- /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
-- /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --
-- Table structure for table `public_holiday`
-- --

-- DROP TABLE IF EXISTS `public_holiday`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `public_holiday` (
--   `date` date NOT NULL,
--   `holiday_name` varchar(50) NOT NULL,
--   PRIMARY KEY (`date`),
--   UNIQUE KEY `UKp37e71hxv2vmmd8qglhkggryw` (`holiday_name`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

-- --
-- Dumping data for table `public_holiday`
-- --

-- LOCK TABLES `public_holiday` WRITE;
-- /*!40000 ALTER TABLE `public_holiday` DISABLE KEYS */;
-- INSERT INTO `public_holiday` VALUES ('2024-02-10','Chinese New Year'),('2024-02-12','Chinese New Year Holiday'),('2024-12-25','Christmas Day'),('2024-10-31','Deepavali'),('2024-03-29','Good Friday'),('2024-06-17','Hari Raya Haji'),('2024-04-10','Hari Raya Puasa'),('2024-05-01','Labour Day'),('2024-08-09','National Day'),('2024-01-01','New Year\'s Day'),('2024-05-22','Vesak Day');
-- /*!40000 ALTER TABLE `public_holiday` ENABLE KEYS */;
-- UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 10:58:30


-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
-- --
-- Host: localhost    Database: laps
-- ------------------------------------------------------
-- Server version	8.4.0

-- /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
-- /*!50503 SET NAMES utf8 */;
-- /*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
-- /*!40103 SET TIME_ZONE='+00:00' */;
-- /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
-- /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
-- /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- /*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- --
-- Table structure for table `staff`
-- --

-- DROP TABLE IF EXISTS `staff`;
-- /*!40101 SET @saved_cs_client     = @@character_set_client */;
-- /*!50503 SET character_set_client = utf8mb4 */;
-- CREATE TABLE `staff` (
--   `user_id` varchar(20) NOT NULL,
--   `email` varchar(25) DEFAULT NULL,
--   `hierarchy` int NOT NULL,
--   `name` varchar(15) DEFAULT NULL,
--   `password` varchar(20) DEFAULT NULL,
--   `status` bit(1) NOT NULL,
--   `department_id` int NOT NULL,
--   PRIMARY KEY (`user_id`),
--   KEY `department_id` (`department_id`),
--   CONSTRAINT `department_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- /*!40101 SET character_set_client = @saved_cs_client */;

-- --
-- Dumping data for table `staff`
-- --

-- LOCK TABLES `staff` WRITE;
-- /*!40000 ALTER TABLE `staff` DISABLE KEYS */;
-- INSERT INTO `staff` VALUES ('111111','123@u.nus.edu',0,'LSW','LSW',_binary '',1),('222222','234@u.nus.edu',0,'CBC','CBC',_binary '',1),('333333','345@u.nus.edu',1,'LC','LC',_binary '',1),('444444','567@u.nus.edu',0,'ZYX','ZYX',_binary '',2),('555555','678@u.nus.edu',1,'FYZT','FYZT',_binary '',2),('666666','789@u.nus.edu',2,'JKY','JKY',_binary '\0',2);
-- /*!40000 ALTER TABLE `staff` ENABLE KEYS */;
-- UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20 10:58:30


