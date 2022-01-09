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
-- Table structure for table `kategori_basliklari`
--

DROP TABLE IF EXISTS `kategori_basliklari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kategori_basliklari` (
  `kategori_no` int NOT NULL,
  `kategori_adi` varchar(45) DEFAULT NULL,
  `baslik_1` varchar(75) DEFAULT NULL,
  `baslik_2` varchar(75) DEFAULT NULL,
  `baslik_3` varchar(75) DEFAULT NULL,
  `baslik_4` varchar(75) DEFAULT NULL,
  `baslik_5` varchar(75) DEFAULT NULL,
  `baslik_6` varchar(75) DEFAULT NULL,
  `baslik_7` varchar(75) DEFAULT NULL,
  `baslik_8` varchar(75) DEFAULT NULL,
  `ust_kategori_no` int NOT NULL,
  PRIMARY KEY (`kategori_no`),
  UNIQUE KEY `kategori_no_UNIQUE` (`kategori_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategori_basliklari`
--

LOCK TABLES `kategori_basliklari` WRITE;
/*!40000 ALTER TABLE `kategori_basliklari` DISABLE KEYS */;
INSERT INTO `kategori_basliklari` VALUES (1,'Laptop','Ekran Kartı','CPU','RAM','Anakart','Ek','','','',1),(2,'Akıllı Telefon','CPU','RAM','İşletim Sistemi',NULL,NULL,NULL,NULL,NULL,1),(3,'Giyilebilir Teknoloji','Tür',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(4,'Yazılım','Geçerlilik Süresi','Desteklediği Platform',NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `kategori_basliklari` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-10  0:58:08
