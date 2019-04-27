-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: secondhand
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.16.04.1

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
-- Table structure for table `shangpin`
--

DROP TABLE IF EXISTS `shangpin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shangpin` (
  `shangpinid` int(11) NOT NULL AUTO_INCREMENT,
  `mincheng` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `yonghuid` int(11) DEFAULT NULL,
  `shijian` date DEFAULT NULL,
  `leibie` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `xijie` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `jiage` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `yuanjia` double DEFAULT NULL,
  `tupian` longtext CHARACTER SET utf8,
  `zhuangtai` int(11) DEFAULT '1',
  `shifoujiajia` int(11) DEFAULT NULL,
  `quhuofangshi` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `xingjiuchengdu` double DEFAULT NULL,
  `liulanrenshu` int(11) DEFAULT NULL,
  PRIMARY KEY (`shangpinid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shangpin`
--

LOCK TABLES `shangpin` WRITE;
/*!40000 ALTER TABLE `shangpin` DISABLE KEYS */;
INSERT INTO `shangpin` VALUES (1,'11111',2,'2019-04-25','游戏交易','111111','11.0',1111,'https://img14.360buyimg.com/n1/jfs/t20695/188/565616716/93445/a0a064d8/5b11163cN2c83f240.jpg',1,NULL,'送货上门',10,NULL),(2,'11111',2,'2019-04-25','游戏交易','111111','12.0',0,'https://img14.360buyimg.com/n1/jfs/t20695/188/565616716/93445/a0a064d8/5b11163cN2c83f240.jpg',1,NULL,'送货上门',10,NULL);
/*!40000 ALTER TABLE `shangpin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-28  1:55:37
