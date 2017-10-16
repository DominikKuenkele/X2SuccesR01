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
-- Table structure for table `branche`
--

DROP TABLE IF EXISTS `branche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branche` (
  `BID` int(11) NOT NULL AUTO_INCREMENT,
  `branche` varchar(100) NOT NULL,
  PRIMARY KEY (`BID`),
  UNIQUE KEY `branche_UNIQUE` (`branche`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branche`
--

LOCK TABLES `branche` WRITE;
/*!40000 ALTER TABLE `branche` DISABLE KEYS */;
INSERT INTO `branche` VALUES (1,'Administration / Verwaltung / Bürowesen'),(2,'Automobil / Automobilzulieferer'),(3,'Banken'),(4,'Baugewerbe / Architektur'),(5,'Beratung / Consulting'),(6,'Bildung / Universität / FH / Schulen'),(7,'Chemie'),(8,'Dienstleistung'),(9,'Druck / Papier / Verpackung'),(10,'EDV / IT'),(11,'Einkauf / Beschaffung'),(12,'Elektro / Elektronik'),(13,'Energiewirtschaft'),(14,'Finanzen'),(15,'Forschung / Entwicklung / Wissenschaft'),(16,'Gesundheitswesen / Soziales / Pflege'),(17,'Handel / Konsum'),(18,'Handwerk'),(19,'Immobilien / Facility Management'),(20,'Industrie'),(21,'Internet / Multimedia'),(22,'Kunst / Kultur / Unterhaltung'),(23,'Marketing / Werbung / PR'),(24,'Marktforschung'),(25,'Maschinen / Anlagenbau'),(26,'Medien'),(27,'Medizin / Pharma'),(28,'Medizintechnik'),(29,'Nahrungsmittel / Land / Forstwirtschaft'),(42,'öffentliche Verwaltung'),(30,'Personalwesen / Personalbeschaffung'),(31,'Rechtsberatung'),(32,'Seminar / Messeanbieter'),(33,'Sonstige Branchen'),(34,'Sport / Fitness / Beauty'),(35,'Steuerberatung / Wirtschaftsprüfung'),(36,'Telekommunikation'),(37,'Textilbranche'),(38,'Tourismus / Hotel / Gastronomie'),(39,'Vereine'),(40,'Verkehr / Transport / Logistik'),(41,'Versicherung');
/*!40000 ALTER TABLE `branche` ENABLE KEYS */;
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
