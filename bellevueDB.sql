CREATE DATABASE  IF NOT EXISTS `bellevuedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bellevuedb`;
-- MySQL dump 10.16  Distrib 10.1.21-MariaDB, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: 127.0.0.1
-- ------------------------------------------------------
-- Server version	10.1.21-MariaDB

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `AccountNo` int(11) NOT NULL,
  `Type` text NOT NULL,
  `Username` text NOT NULL,
  `Password` text NOT NULL,
  PRIMARY KEY (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Admin','admin','1234'),(2,'Secretary','secretary','123');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection` (
  `UnitNo` int(11) NOT NULL,
  `DatePaid` datetime NOT NULL,
  `BillingDate` datetime NOT NULL,
  KEY `fk_UnitNo_idx` (`UnitNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (101,'2017-10-15 00:00:00','2017-10-15 00:00:00'),(102,'2017-10-02 00:00:00','2017-10-15 00:00:00'),(103,'2017-10-15 00:00:00','2017-10-15 00:00:00'),(104,'2017-10-20 00:00:00','2017-10-15 00:00:00'),(105,'2017-10-02 00:00:00','2017-10-15 00:00:00'),(106,'2017-10-18 00:00:00','2017-10-15 00:00:00'),(107,'2017-10-18 00:00:00','2017-10-15 00:00:00'),(108,'2017-10-01 00:00:00','2017-10-15 00:00:00'),(109,'2017-10-01 00:00:00','2017-10-15 00:00:00'),(110,'2017-10-15 00:00:00','2017-10-15 00:00:00'),(111,'2017-10-01 00:00:00','2017-10-15 00:00:00'),(112,'2017-10-20 00:00:00','2017-10-15 00:00:00'),(113,'2017-10-01 00:00:00','2017-10-15 00:00:00'),(114,'2017-10-15 00:00:00','2017-10-15 00:00:00'),(305,'2017-10-01 00:00:00','2017-10-15 00:00:00'),(306,'2017-10-15 00:00:00','2017-10-15 00:00:00'),(401,'2017-10-15 00:00:00','2017-10-15 00:00:00'),(402,'2017-10-01 00:00:00','2017-10-15 00:00:00'),(403,'2017-10-18 00:00:00','2017-10-15 00:00:00'),(405,'2017-10-01 00:00:00','2017-10-15 00:00:00'),(406,'2017-10-29 00:00:00','2017-10-15 00:00:00'),(407,'2017-10-15 00:00:00','2017-10-15 00:00:00'),(408,'2017-10-17 00:00:00','2017-10-15 00:00:00'),(0,'0000-00-00 00:00:00','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee` (
  `FeeID` int(11) NOT NULL AUTO_INCREMENT,
  `FeeName` text NOT NULL,
  `price` double NOT NULL,
  `Type` text NOT NULL,
  PRIMARY KEY (`FeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
INSERT INTO `fee` VALUES (1,'Car Sticker',250,'Basic Charges'),(2,'Overnight Parking',100,'Basic Charges'),(3,'Breaker',50,'Others'),(4,'Flyers',10,'Others'),(5,'Household ID',50,'Others'),(6,'Photocopy',1,'Others'),(7,'Trash bags',10,'Others'),(8,'Basketball Court',100,'Rentals'),(9,'Billiard',250,'Rentals'),(10,'Clubhouse',1500,'Rentals'),(11,'Lounge',2000,'Rentals'),(12,'Swimming Pool',2500,'Rentals'),(13,'Furniture',50,'Rentals'),(14,'Real Property Tax',5000,'Rentals'),(15,'Construction Shirts',200,'Renovation'),(16,'Construction ID',50,'Renovation'),(17,'Basketball League',50,'Special Activities and Events'),(18,'Mooncake Festival',100,'Special Activities and Events'),(19,'Christmas Party',250,'Special Activities and Events'),(20,'Taekwondo',1000,'Special Activities and Events'),(21,'Others',0,'Special Activities and Events'),(22,'Category A',2750,'Monthly Association Dues'),(23,'Category B',3000,'Monthly Association Dues'),(24,'Category C',3250,'Monthly Association Dues');
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monthlydues`
--

DROP TABLE IF EXISTS `monthlydues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthlydues` (
  `DueID` int(11) NOT NULL,
  `Category` text NOT NULL,
  `Floor` int(11) NOT NULL,
  `Ceiling` int(11) NOT NULL,
  PRIMARY KEY (`DueID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthlydues`
--

LOCK TABLES `monthlydues` WRITE;
/*!40000 ALTER TABLE `monthlydues` DISABLE KEYS */;
INSERT INTO `monthlydues` VALUES (1,'A',100,150),(2,'B',150,200),(3,'C',200,250);
/*!40000 ALTER TABLE `monthlydues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `UnitNo` int(11) NOT NULL,
  `BilledTo` text NOT NULL,
  `TCT` text NOT NULL,
  `AdresssNo` int(11) NOT NULL,
  `Street` text NOT NULL,
  `Lot Area` int(11) NOT NULL,
  `Category` text NOT NULL,
  `PhaseNo` int(11) NOT NULL,
  PRIMARY KEY (`UnitNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (101,'Philip Chan','ROXON PRINTING CORP.',25,'Acacia Drive',148,'A',1),(102,'Elizabeth Lim','LIM KAM CHEUNG',23,'Acacia Drive',148,'A',1),(103,'Lionel Cheng','BELLWOOD ENTERPRISES',21,'Acacia Drive',150,'A',1),(104,'Adeline Co','ADELINE ANG HONG',19,'Acacia Drive',191,'B',1),(105,'Pablo Tan','NANCY PACUAN',17,'Acacia Drive',191,'B',1),(106,'Richmond Tee','RICHMOND NGO TEE',15,'Acacia Drive',149,'A',1),(107,'Shirleybeth Lugay','SHIRLEYBETH CHAN LUGAY',11,'Acacia Drive',148,'A',1),(108,'Milliecent Sanhi','MILLIECENT ONG SANHI',9,'Acacia Drive',149,'A',1),(109,'Aurora Chua','AURORA CHUA',7,'Acacia Drive',149,'A',1),(110,'Marvin Li','ANNALIZA LI',5,'Acacia Drive',148,'A',1),(111,'Johnson Huang','KENNETH G. UY',3,'Acacia Drive',148,'A',1),(112,'Fely Ng','FAIRY JOY T. NG & FAIRY GAILLE T. NG',1,'Acacia Drive',148,'A',1),(113,'Ernesto Go, Jr.','ERNESTO TAN GO, JR.',1,'Palm Drive',197,'B',1),(114,'Thomas Chua','UNI-ATLANTIC COTTON MILLS',3,'Palm Drive',161,'B',1),(305,'Christine Gaw','SPS. MALVIN J. ESTEBAN & CAROLINE T. ESTEBAN',9,'North Park Drive',192,'B',2),(306,'Walter Ross Go','',11,'North Park Drive',192,'B',2),(401,'Benson Go','BENSON GO',36,'Acacia Drive',216,'C',2),(402,'Arthur See','ARTHUR SEE',38,'Acacia Drive',157,'B',2),(403,'Adolfo Yubek','WILLIE OCAMPO & GARY OCAMPO',40,'Acacia Drive',157,'B',2),(405,'Temao Phils. Int\'l. Co.','TEMAO PHILS INTERNATIONAL CORP.',42,'Acacia Drive',157,'B',2),(406,'Joan Kang','JO-AN O. GO KANG',36,'Fountain Drive',184,'B',2),(407,'Ong Hao Lai Nai','HAO LAI NA',38,'Fountain Drive',157,'B',2),(408,'Mark Lu','MARK LU',40,'Fountain Drive',157,'B',2);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'bellevuedb'
--

--
-- Dumping routines for database 'bellevuedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-21 23:27:37
