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
-- Table structure for table `ust_kategori_basliklari`
--

DROP TABLE IF EXISTS `ust_kategori_basliklari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ust_kategori_basliklari` (
  `ust_kategori_basliklari_no` int NOT NULL,
  `ust_kategori_ad` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik1` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik2` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik3` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik4` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik5` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik6` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik7` varchar(45) DEFAULT NULL,
  `ust_kategori_baslik8` varchar(45) DEFAULT NULL,
  `alt_kategori_mevcut` tinyint NOT NULL,
  PRIMARY KEY (`ust_kategori_basliklari_no`),
  UNIQUE KEY `ust_kategori_basliklari_no_UNIQUE` (`ust_kategori_basliklari_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ust_kategori_basliklari`
--

LOCK TABLES `ust_kategori_basliklari` WRITE;
/*!40000 ALTER TABLE `ust_kategori_basliklari` DISABLE KEYS */;
INSERT INTO `ust_kategori_basliklari` VALUES (1,'Teknoloji','Marka','Model','Üretim Yılı',NULL,NULL,NULL,NULL,NULL,1),(2,'Giyim','Marka','Beden','Renk',NULL,NULL,NULL,NULL,NULL,0),(3,'Kitap','Tür','Yazar Adı','Konu','Yayınevi','Basım Yılı','Baskı Sayısı','Kitap Sayfası',NULL,0),(4,'Gıda','Son Kullanma Tarihi','Gramaj Bilgisi',NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `ust_kategori_basliklari` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-10  0:58:06
