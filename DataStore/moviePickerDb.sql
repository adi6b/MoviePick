CREATE DATABASE  IF NOT EXISTS `moviePickerEI2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `moviePickerEI2`;
-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: moviePickerEI2
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Movies`
--

DROP TABLE IF EXISTS `Movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Movies` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(200) DEFAULT NULL,
  `movie_desc` varchar(200) DEFAULT NULL,
  `movie_rating` decimal(2,1) DEFAULT NULL,
  `votes_numb` int(11) DEFAULT NULL,
  `movie_genre` varchar(45) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  UNIQUE KEY `movie_id_UNIQUE` (`movie_id`),
  UNIQUE KEY `movie_name_UNIQUE` (`movie_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movies`
--

LOCK TABLES `Movies` WRITE;
/*!40000 ALTER TABLE `Movies` DISABLE KEYS */;
INSERT INTO `Movies` VALUES (1,'The Lego Movie 2: The Second Part','This is an animation movie on toys',4.2,10,'animation','2019-02-08'),(2,'Alita: Battle Angel','This is an action movie on toys',4.5,10,'animation','2019-02-01'),(3,'Isnt It Romantic','A story of a young couple',3.5,10,'romance','2019-02-01'),(4,'The Prodigy','A story of a young boy',3.1,10,'history','2019-02-08'),(5,'The Upside','A story of a caretaker',4.1,10,'drama','2019-02-08'),(8,'Titanic','Best Nolan Movie Ever',4.8,10,'adventure','2011-12-31'),(9,'Titanic66','Best Nolan Movie Ever',4.9,10,'adventure','2011-12-31'),(10,'Avatar','On another planet',4.9,10,'adventure','2011-12-31'),(11,'Batman','Best Nolan Movie Ever',4.9,10,'adventure','2011-12-31'),(12,'Batman Returns','Best Nolan Movie Ever',4.9,10,'adventure','2019-12-31'),(13,'Tumbad','Konkani',3.8,10,'thriller','2017-12-31'),(15,'Grand Tour1','It is a car show',4.8,10,'sports','2019-01-12'),(16,'Grand Tour','It is a car show',4.8,10,'sports','2019-12-31');
/*!40000 ALTER TABLE `Movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtimes`
--

DROP TABLE IF EXISTS `showtimes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `showtimes` (
  `movie_id` int(11) DEFAULT NULL,
  `theatre_id` int(11) NOT NULL,
  `movie_showtimings` datetime NOT NULL,
  PRIMARY KEY (`theatre_id`,`movie_showtimings`),
  KEY `theatre_id_idx` (`theatre_id`),
  CONSTRAINT `theatre_id` FOREIGN KEY (`theatre_id`) REFERENCES `theatre_info` (`theatre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtimes`
--

LOCK TABLES `showtimes` WRITE;
/*!40000 ALTER TABLE `showtimes` DISABLE KEYS */;
INSERT INTO `showtimes` VALUES (1,1,'2019-02-14 14:30:00'),(1,1,'2019-02-14 21:00:00'),(1,1,'2019-02-15 14:30:00'),(2,1,'2019-02-15 15:30:00'),(2,2,'2019-02-15 01:30:00');
/*!40000 ALTER TABLE `showtimes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre_info`
--

DROP TABLE IF EXISTS `theatre_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `theatre_info` (
  `theatre_id` int(11) NOT NULL DEFAULT '1',
  `theatre_name` varchar(45) DEFAULT NULL,
  `theatre_location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`theatre_id`),
  UNIQUE KEY `theatre_id_UNIQUE` (`theatre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre_info`
--

LOCK TABLES `theatre_info` WRITE;
/*!40000 ALTER TABLE `theatre_info` DISABLE KEYS */;
INSERT INTO `theatre_info` VALUES (1,'AMC','Athens'),(2,'Movie Taveren','Athens'),(3,'Regal Cinemas','Athens');
/*!40000 ALTER TABLE `theatre_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-14 20:37:27
