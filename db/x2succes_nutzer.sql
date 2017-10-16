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
-- Table structure for table `nutzer`
--

DROP TABLE IF EXISTS `nutzer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nutzer` (
  `NID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(15) NOT NULL,
  `lastName` varchar(15) NOT NULL,
  `sexID` int(11) DEFAULT NULL,
  `birthdate` date NOT NULL,
  `eMail` varchar(40) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `plz` varchar(5) NOT NULL,
  `city` varchar(20) NOT NULL,
  `street` varchar(30) NOT NULL,
  `number` varchar(4) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`NID`),
  UNIQUE KEY `eMail` (`eMail`),
  KEY `FK_SexNutzer` (`sexID`),
  CONSTRAINT `FK_SexNutzer` FOREIGN KEY (`sexID`) REFERENCES `sex` (`sexID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutzer`
--

LOCK TABLES `nutzer` WRITE;
/*!40000 ALTER TABLE `nutzer` DISABLE KEYS */;
INSERT INTO `nutzer` VALUES (22,'Dominik','Künkele',1,'1999-02-05','dominik.kuenkele@outlook.com','1000:54f6770b001e14bcc400d8ef0f61920f:542e9183670d65fefc89aaade311d4432f1aac81cfa71b0d0add651fac34815607f01dc46d71d53780b7abbae78fbbf8db1fa51f8f14e6cf820983a3495dd296','71384','Weinstadt','Teckstraße','19','U'),(29,'Tim','Bareiß',1,'2017-10-02','tim@test.de','1000:e65d980397211f71c68d968b1136bbef:00185082b1f190547cd6829ed8d7862219c2fa963579523fd5eaf8bee67fb2ada296640f6b14d17cb417359b5728b0cbfcede00877be55074ba1723d17ea9cfb','33333','Welzheim','dfdf','2','F'),(30,'Björn','Konzmann',1,'2017-10-02','bjoern@test.de','1000:2e07645b108f75c2eefef8b112879888:2a8fb97d3045710a8f8544e2d7ff7e1e521d3fa5c7a1f18eb20ae83a1cbb4c035aec52871f306243b83f828afffb262b6666d98bf37fd656bd66db9371c2ad39','66646','Stetten','Straße','3','F'),(31,'Marian','Finkbeiner',1,'2017-10-02','marian@test.de','1000:0e1c6e61e4ebcbea9011507a4c795d98:fe91250dc315cf97b409460f8dcbedc4e12ed80c21922159fcefefdb1c93f1fcf4e2845578845b9b7cd9e88f87eb076b25b65151aba3852bb698bdf8e837936e','44444','Winnenden','Straße','2','F'),(32,'Aylin','Yalcin',2,'2017-10-03','aylin@test.de','1000:8158c7a4a9d2add0df64e3e3b827f6f3:e6e610f25e19cd1a76a026bf0e3c1dfc7820cafdcaf1371409f45ca849281b71f0a713e05a720acc67b0a11f63106c3c8d12e36f6856c4e447301258224c8b4d','55555','Ludwigsburg','Straße','2','F'),(33,'Tim','Tim',3,'2017-10-15','a@bc.de','1000:a595b6fbc5137c62f52bfa490b9de8c7:9b0bc0b668e8e3c714b403e0316a78cb6936b5e75ef57e658244cdec75ed5513e631cbc3281df86f92e72c5dcbd4ec38ab9824de0731f79eaa999b26983d1511','73642','Welzheim','Irgendwaseinfach','3','U');
/*!40000 ALTER TABLE `nutzer` ENABLE KEYS */;
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
