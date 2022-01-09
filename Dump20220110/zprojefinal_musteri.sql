-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: zprojefinal
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `musteri`
--

DROP TABLE IF EXISTS `musteri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `musteri` (
  `musteri_no` int NOT NULL,
  `musteri_ad_soyad` varchar(45) NOT NULL,
  `musteri_eklenme_tarih` datetime NOT NULL,
  `musteri_telefon` varchar(45) NOT NULL,
  `musteri_adres` varchar(95) NOT NULL,
  `musteri_eposta` varchar(45) NOT NULL,
  `musteri_son_islem_tarih` datetime NOT NULL,
  `musteri_toplam_harcama` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`musteri_no`),
  UNIQUE KEY `musteri_no_UNIQUE` (`musteri_no`),
  UNIQUE KEY `musteri_telefon_UNIQUE` (`musteri_telefon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musteri`
--

LOCK TABLES `musteri` WRITE;
/*!40000 ALTER TABLE `musteri` DISABLE KEYS */;
INSERT INTO `musteri` VALUES (1,'Ali Dereli','2014-04-04 14:12:44','+090555555555','ANKARA','ali@dereli.com','0001-01-01 00:00:00',145321.44),(2,'Hamit Elidemir','2018-04-04 14:12:44','+905045678900','İZMİR','hamit@elidemir.com','2019-04-04 14:12:44',0.00),(3,'13.Kat Barosu','2020-08-14 15:04:01','+903122222222','ANKARA','av.adem@ankarabaro.com','0001-01-01 00:00:00',3199.90),(4,'Rıfat Taşmermi','2022-01-01 06:10:20','+904558522243','NİĞDE','rifat@tasmermi.com','0001-01-01 00:00:00',0.00),(5,'Yiğit Kıratlı','2022-01-01 09:42:03','+905478774547','Beylerbeyi Mah. 441.Sokak No:58/67 Çankaya/ANKARA','yiğit@kiratli.com','0001-01-01 00:00:00',0.00),(6,'Tefik Alaca','2022-01-01 09:43:11','+905468774144','TEKİRDAĞ','tefik@alaca.com','0001-01-01 00:00:00',0.00),(7,'Çağdaş Kemik','2022-01-01 09:52:37','+95656984124','UŞAK','cagdas@kemik.com','0001-01-01 00:00:00',0.00),(8,'Adem İzzet Bostan','2022-01-01 14:55:06','+905477864477','Hakyolu Mah. Dereboyu Sokak No:47/5 Seyhan/ADANA','izzet@bostan.com','2022-01-10 00:03:44',3199.90);
/*!40000 ALTER TABLE `musteri` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-10  0:58:07
