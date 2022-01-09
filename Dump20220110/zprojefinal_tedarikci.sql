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
-- Table structure for table `tedarikci`
--

DROP TABLE IF EXISTS `tedarikci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tedarikci` (
  `tedarikci_no` int NOT NULL,
  `tedarikci_ad` varchar(45) NOT NULL,
  `tedarikci_sorumlu` varchar(45) NOT NULL,
  `tedarikci_merkez_adres` varchar(45) NOT NULL,
  `tedarikci_dagitim_adres` varchar(45) NOT NULL,
  `tedarikci_telefon` varchar(45) NOT NULL,
  `tedarikci_eposta` varchar(45) NOT NULL,
  `tedarikci_web` varchar(45) NOT NULL,
  `tedarikci_kargo_1` varchar(45) NOT NULL,
  `tedarikci_kargo_2` varchar(45) DEFAULT NULL,
  `tedarikci_kargo_3` varchar(45) DEFAULT NULL,
  `tedarikci_kargo_4` varchar(45) DEFAULT NULL,
  `tedarikci_teslimat_ortalama` int NOT NULL,
  `tedarikci_toplam_alim` decimal(12,2) NOT NULL,
  `tedarikci_eklenme_tarih` datetime NOT NULL,
  `tedarikci_son_islem_tarih` datetime NOT NULL,
  PRIMARY KEY (`tedarikci_no`),
  UNIQUE KEY `tedarikci_no_UNIQUE` (`tedarikci_no`),
  UNIQUE KEY `tedarikci_telefon_UNIQUE` (`tedarikci_telefon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tedarikci`
--

LOCK TABLES `tedarikci` WRITE;
/*!40000 ALTER TABLE `tedarikci` DISABLE KEYS */;
INSERT INTO `tedarikci` VALUES (1,'Ülker Depo','Murat Ali Değirmen','İSTANBUL','ANKARA','+905774885596','ülker@ülker.com','ülker.com','PTT Kargo','Yurtiçi Kargo','MNG Kargo','Aras Kargo + Ucuz Kargo',0,0.00,'2019-01-01 16:17:45','0001-01-01 00:00:00'),(2,'Vatan Bilgisayar Depo','Rüstem Balcı','İSTANBUL','ANKARA','+903125124565','vatan@pc.com','vatanbilgisayar.com','UPS Kargo','Yurtiçi Kargo','PTT Kargo',NULL,37,4689752.00,'2019-08-17 09:17:45','2022-01-01 16:17:45'),(3,'ANKARA Depo','Polat Kaleli','ANKARA','ANKARA','+903124558877','ankaradepo@merkez.com','ankaradepo.com','Yurtiçi Kargo','MNG Kargo',NULL,NULL,0,0.00,'2022-01-01 16:17:45','0001-01-01 00:00:00'),(4,'Apple Store','Yusuf İlerigelen','New-York','PEKİN','+18764585885','apple@apple.com','apple.com','International Ship',NULL,'','',0,0.00,'2022-01-01 16:43:43','0001-01-01 00:00:00');
/*!40000 ALTER TABLE `tedarikci` ENABLE KEYS */;
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
