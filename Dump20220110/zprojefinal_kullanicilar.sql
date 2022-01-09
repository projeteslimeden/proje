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
-- Table structure for table `kullanicilar`
--

DROP TABLE IF EXISTS `kullanicilar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kullanicilar` (
  `kullanici_no` int unsigned NOT NULL,
  `kullanici_id` varchar(18) NOT NULL,
  `kullanici_kripto_sifre` varchar(20) NOT NULL,
  `kullanici_ad` varchar(25) NOT NULL,
  `kullanici_soyad` varchar(20) NOT NULL,
  `kullanici_dogum_tarih` date NOT NULL,
  `kullanici_eklenme_tarih` datetime NOT NULL,
  `kullanici_telefon` varchar(13) NOT NULL,
  `kullanici_eposta` varchar(45) NOT NULL,
  `kullanici_adres` varchar(45) NOT NULL,
  `kullanici_aylik_maas` decimal(8,2) NOT NULL,
  `kullanici_prim` decimal(8,2) NOT NULL,
  `kullanici_seviyesi` int NOT NULL,
  PRIMARY KEY (`kullanici_no`),
  UNIQUE KEY `kullanici_no_UNIQUE` (`kullanici_no`),
  UNIQUE KEY `kullanici_id_UNIQUE` (`kullanici_id`),
  UNIQUE KEY `kullanici_telefon_UNIQUE` (`kullanici_telefon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kullanicilar`
--

LOCK TABLES `kullanicilar` WRITE;
/*!40000 ALTER TABLE `kullanicilar` DISABLE KEYS */;
INSERT INTO `kullanicilar` VALUES (1,'Admin','Rodd','Kemal','Düzgün','1996-05-05','0001-01-01 00:00:00','+907777777777','kemal@duzgun.com','ANKARA',449900.88,7428.71,1),(2,'GM','Rodd','Ali','Taşlıcalı','1985-03-07','2020-10-17 11:11:11','+905555555555','ali@taslicali.com','İSTANBUL',4889.00,809.00,2),(3,'Muho','Rodd','Şafak','Terzi','1987-11-02','2021-05-12 11:11:11','+906666666666','safak@terzi.com','İZMİR',4253.00,105.46,4),(4,'Kasiyer','Rodd','Yavuz','Cebeci','1991-07-25','2021-12-26 11:01:42','+902222222222','yavuz@cebeci.com','ADANA',4253.00,0.00,5),(5,'IK','Rodd','Bekir','Temizel','1984-02-29','2021-12-26 13:04:13','+908888888888','bekir@temizel.com','ZONGULDAK',4253.00,54.72,3),(6,'SS','Rodd','SS','SS','1984-02-29','2021-12-26 13:04:13','+908888878888','bekir@temizel.com','ZONGULDAK',4253.00,54.72,6),(7,'YeniKasiyer','cXI\"cIB&0<','Şefik','Polat','1981-07-05','2022-01-08 20:26:35','+905004145878','sefik@polat.com','ANKARA',4350.98,0.00,2);
/*!40000 ALTER TABLE `kullanicilar` ENABLE KEYS */;
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
