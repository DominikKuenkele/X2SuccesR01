-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: x2succes
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jobangebot`
--

DROP TABLE IF EXISTS `jobangebot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobangebot` (
  `JID` int(11) NOT NULL AUTO_INCREMENT,
  `UID` int(11) DEFAULT NULL,
  `GID` int(11) DEFAULT NULL,
  `EID` int(11) DEFAULT NULL,
  `jobTitle` varchar(30) DEFAULT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `weeklyHours` int(11) DEFAULT NULL,
  PRIMARY KEY (`JID`),
  KEY `graduation_ibfk_1_idx` (`GID`),
  KEY `graduation_ibfk_2_idx` (`GID`),
  KEY `jobangebot_ibfk_1` (`UID`),
  KEY `EID` (`EID`),
  CONSTRAINT `jobangebot_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `unternehmensprofil` (`UID`),
  CONSTRAINT `jobangebot_ibfk_3` FOREIGN KEY (`EID`) REFERENCES `expertise` (`EID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobangebot`
--

LOCK TABLES `jobangebot` WRITE;
/*!40000 ALTER TABLE `jobangebot` DISABLE KEYS */;
INSERT INTO `jobangebot` VALUES (1,1,5,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(2,1,6,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(3,1,7,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(4,1,4,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(5,7,5,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(6,8,6,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(7,9,7,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(8,10,4,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(9,11,5,1,'Burgerbrater','beschreibung','2019-10-05',2000,20),(10,1,4,1,'sdfds','sfsdf','1050-12-31',2333,20),(11,1,4,1,'Jobangebot','adsasd','1050-12-31',2000,20),(12,1,6,4,'Burgerbrater','Burgerbraten','1050-12-31',100,50);
/*!40000 ALTER TABLE `jobangebot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-16 10:36:02
