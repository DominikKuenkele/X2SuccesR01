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
-- Table structure for table `unternehmensprofil`
--

DROP TABLE IF EXISTS `unternehmensprofil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unternehmensprofil` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `NID` int(11) NOT NULL,
  `BID` int(11) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `legalForm` varchar(15) DEFAULT NULL,
  `founding` date DEFAULT NULL,
  `employees` int(11) DEFAULT NULL,
  `description` varchar(5000) NOT NULL,
  `website` varchar(60) DEFAULT NULL,
  `ceoFirstName` varchar(30) DEFAULT NULL,
  `ceoLastName` varchar(30) DEFAULT NULL,
  `plz` varchar(5) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `street` varchar(20) DEFAULT NULL,
  `number` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `NID` (`NID`),
  KEY `unternehmensprofil_ibfk_2_idx` (`BID`),
  CONSTRAINT `unternehmensprofil_ibfk_1` FOREIGN KEY (`NID`) REFERENCES `nutzer` (`NID`),
  CONSTRAINT `unternehmensprofil_ibfk_2` FOREIGN KEY (`BID`) REFERENCES `branche` (`BID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unternehmensprofil`
--

LOCK TABLES `unternehmensprofil` WRITE;
/*!40000 ALTER TABLE `unternehmensprofil` DISABLE KEYS */;
INSERT INTO `unternehmensprofil` VALUES (1,22,2,'MAHLE','GmbH','1917-12-13',80000,'Coole Firma!','www.mahle.com','Wolf','Henning Scheider','71456','Stuttgart','Pragstraße','46'),(7,22,2,'BurgerKing','GmbH','1244-12-04',0,'df','www.fg.vv','sdg','fs','12345','kjhsk','gslkfjg','23'),(8,22,2,'Daimler','GmbH','1244-12-04',100,'df','www.fg.vv','sdg','fs','12345','kjhsk','gslkfjg','23'),(9,22,1,'Apple','GmbH','1244-12-04',200,'df','www.fg.vv','sdg','fs','12345','kjhsk','gslkfjg','23'),(10,22,1,'Microsoft','GmbH','1244-12-04',300,'df','www.fg.vv','sdg','fs','12345','kjhsk','gslkfjg','23'),(11,22,1,'Opel','GmbH','1244-12-04',400,'df','www.fg.vv','sdg','fs','12345','kjhsk','gslkfjg','23'),(12,22,18,'Bosch','GmbH','2011-02-02',30,'Beschreibung','www.test.de','Vorname','Nachname','66663','Weinstadt','Straße','2'),(13,22,20,'MAHLE','GmbH','2017-10-02',80000,'Beschreibung','www.test.de','Vorname','Nachname','71111','Stuttgart','Pragstraße','26');
/*!40000 ALTER TABLE `unternehmensprofil` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-16 10:36:01
