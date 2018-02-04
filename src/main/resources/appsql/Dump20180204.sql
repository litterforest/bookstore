-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	5.6.30

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
-- Table structure for table `tbook`
--

DROP TABLE IF EXISTS `tbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbook` (
  `isbn` varchar(20) NOT NULL,
  `bname` varchar(50) DEFAULT NULL,
  `press` varchar(50) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `pic` blob,
  `picurl` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbook`
--

LOCK TABLES `tbook` WRITE;
/*!40000 ALTER TABLE `tbook` DISABLE KEYS */;
INSERT INTO `tbook` VALUES ('00001','三国','清华出版社',56.00,'2017-12-24',NULL,'/resources/images/shanguo.png'),('00002','水浒','机械出版社',40.00,'2017-12-24',NULL,'/resources/images/shuixu.png'),('00003','红楼梦','外文出版社',46.00,'2018-02-05',NULL,'/resources/images/hongluomen.jpg');
/*!40000 ALTER TABLE `tbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuydetail`
--

DROP TABLE IF EXISTS `tbuydetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbuydetail` (
  `autoid` decimal(9,0) NOT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `buyid` decimal(9,0) DEFAULT NULL,
  `bcount` decimal(3,0) DEFAULT NULL,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuydetail`
--

LOCK TABLES `tbuydetail` WRITE;
/*!40000 ALTER TABLE `tbuydetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbuydetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuyrecord`
--

DROP TABLE IF EXISTS `tbuyrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbuyrecord` (
  `buyid` decimal(9,0) NOT NULL,
  `uname` varchar(20) DEFAULT NULL,
  `buytime` date DEFAULT NULL,
  `allmoney` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`buyid`),
  KEY `FK_TBUYRECO_REFERENCE_TUSER` (`uname`),
  CONSTRAINT `FK_TBUYRECO_REFERENCE_TUSER` FOREIGN KEY (`uname`) REFERENCES `tuser` (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuyrecord`
--

LOCK TABLES `tbuyrecord` WRITE;
/*!40000 ALTER TABLE `tbuyrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbuyrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tuser`
--

DROP TABLE IF EXISTS `tuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tuser` (
  `uname` varchar(20) NOT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `account` decimal(5,2) DEFAULT NULL,
  `role` char(1) DEFAULT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tuser`
--

LOCK TABLES `tuser` WRITE;
/*!40000 ALTER TABLE `tuser` DISABLE KEYS */;
INSERT INTO `tuser` VALUES ('admin','123456',999.99,'1');
/*!40000 ALTER TABLE `tuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-04 22:12:35
