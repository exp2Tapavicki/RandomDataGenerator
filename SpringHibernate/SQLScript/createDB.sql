-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: db
-- ------------------------------------------------------
-- Server version   5.7.18

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
-- Table structure for table `app_user`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `db` /*!40100 DEFAULT CHARACTER SET latin1 */;

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sso_id` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sso_id` (`sso_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

LOCK TABLES `app_user` WRITE;
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` VALUES (1,'admin','$2a$10$Wx7oJQ7v4nO5wBG1f7lBieviNZ15B3bTfbagRE4Uk0cNVbDf93XkO','admin','admin','admin@admin.admin'),(2,'dba','$2a$10$Wx7oJQ7v4nO5wBG1f7lBieviNZ15B3bTfbagRE4Uk0cNVbDf93XkO','dba','dba','dba@dba.dba'),(3,'user','$2a$10$Wx7oJQ7v4nO5wBG1f7lBieviNZ15B3bTfbagRE4Uk0cNVbDf93XkO','user','user','user@user.user'),(4,'testuser','$2a$10$tyBq8ASxqR7STbMt.0NrPe0g.hETooKTfXB00KRIRivmXei7rCgr6','testuser','testuser','testuser@testuser.testuser');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_user_user_profile`
--

DROP TABLE IF EXISTS `app_user_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user_user_profile` (
  `user_id` bigint(20) NOT NULL,
  `user_profile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_user_profile`
--

LOCK TABLES `app_user_user_profile` WRITE;
/*!40000 ALTER TABLE `app_user_user_profile` DISABLE KEYS */;
INSERT INTO `app_user_user_profile` VALUES (3,1),(4,1),(1,2),(2,3);
/*!40000 ALTER TABLE `app_user_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `jmbg` varchar(50) NOT NULL,
  `year_of_birth` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `hired_after` tinyint(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
INSERT INTO `applicant` VALUES (11,'Courtney','Amtower','ca6abe4e-72f9-4ea0-8060-b2ed1c3d7371',1960,'sarah.kensinger@somewhere.org','f1a6e4dd-8eee-4545-8e3f-a5d4ef099514','9ee3bd14-795b-4fda-9b5e-3dc6dcc335da',0,'1912-03-08 03:27:32'),(12,'Laura','Shellhammer','156977e4-d489-4d58-8550-1be97eebe62e',1948,'colby.nichols@somewhere.org','312a7195-edee-4990-9a87-f555bd27373e','73c40025-7f05-42aa-acfe-e9978ee53b18',1,'1983-07-30 05:18:12'),(13,'Alison','Hamilton','9b574eb8-049d-4986-8e08-960c9fa73117',1959,'leslie.dawe@somewhere.org','c50ca99d-fee0-405b-a8dd-69b7c455e89b','0334b09f-6d71-4d6a-b32f-36b6df9dd765',0,'2025-08-14 02:14:29'),(14,'Isaiah','Bannister','047aef12-07e3-44d7-a7f0-03a56944ea1b',1928,'cindy.settle@somewhere.org','5ec818f6-29eb-4310-9ed1-de70a5234c43','b2fd1365-5317-4882-9443-e70ae3ee2716',1,'1973-02-04 16:12:19'),(15,'Kate','Yost','9d2daaf5-cee3-4940-94aa-cab3dc63a89c',1951,'monica.brown@somewhere.org','377a28f3-3234-49bc-a658-93c5bed33982','b70ebc2a-dc58-4391-b8da-b7d527133ff5',0,'1961-12-01 10:59:11'),(16,'Perry','Schmidt','d693945b-3857-46d5-8229-5d09b4565c35',1959,'joe.mcdonald@somewhere.org','6bedd630-e2d5-43d8-a0a3-f702b3798738','2b2a05fb-67e0-41b9-9981-d512af68e856',1,'1939-07-08 00:05:22'),(17,'Theresa','Krippner','50150cb0-8e0d-48fb-b71b-b6df5b40ef2e',1961,'alethea.hastings@somewhere.org','2dc9e25b-3418-4eaf-a9b7-3400d8e76d55','1f60293b-fabe-455d-8ee1-a4401e6ac66b',0,'1950-01-02 07:35:38'),(18,'Angela','McDonald','02a73450-4c5d-4d3e-a8b4-8c61ba4a3b60',1977,'shirley.grant@somewhere.org','f09881a7-0bdb-484a-b0b7-20e97374f12d','3223db25-70ca-4690-85e4-83c054e586f8',0,'1974-09-02 12:34:33'),(19,'Susie','Southard','67dfd5ed-fa95-4fd3-96af-395e79a92cb0',1960,'jeremy.ganoe@somewhere.org','fe58ce7d-303a-44bf-af11-c88708797b11','1b616189-4cca-4f19-93ec-2707aa50857d',1,'1978-07-08 00:12:31'),(20,'Shirley','Zimmerman','547868dd-55e8-46d5-a2e9-835f1ecb8a55',1975,'nicole.faircloth@somewhere.org','63456744-cbc2-45f8-9f14-2abf7401714e','3e979dad-0edb-4320-b5ae-289bfe7349ab',1,'1997-08-03 14:38:28');
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `random_data_generation`
--

DROP TABLE IF EXISTS `random_data_generation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `random_data_generation` (
  `id` bigint(20) NOT NULL,
  `ordinal_number` bigint(20) NOT NULL,
  `basic_class_constants` varchar(30) NOT NULL,
  `property_name` varchar(30) NOT NULL,
  `obj_min` varchar(50) NOT NULL,
  `obj_max` varchar(50) NOT NULL,
  `obj_precision` varchar(30) NOT NULL,
  `b_allow_nulls` tinyint(1) DEFAULT NULL,
  `obj_enum` varchar(250) NOT NULL,
  PRIMARY KEY (`id`,`ordinal_number`),
  CONSTRAINT `fk_random_data_generation_model` FOREIGN KEY (`id`) REFERENCES `random_data_generation_model` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `random_data_generation`
--

LOCK TABLES `random_data_generation` WRITE;
/*!40000 ALTER TABLE `random_data_generation` DISABLE KEYS */;
INSERT INTO `random_data_generation` VALUES (1,1,'java.lang.Integer','Integer','0','150','5',1,''),(1,2,'java.lang.Long','Long','0','200','',1,''),(1,3,'java.lang.Short','Short','0','65','',1,''),(1,4,'java.lang.Byte','Byte','0','15','',1,''),(1,5,'java.lang.Boolean','Boolean','','','',1,''),(1,6,'java.lang.Double','Double','100.00000','500.00000','5',1,''),(1,7,'java.lang.Float','Float','0.000','120.000','5',1,''),(1,8,'java.lang.Character','Character','65','90','',1,''),(1,9,'java.lang.String','name','','','',1,''),(1,10,'java.lang.String','email','','','',1,''),(1,11,'java.util.Date','date','1900/01/01 00:00:00','2020/01/01 00:00:00','',1,''),(1,12,'int','int','0','6000','',0,''),(1,13,'long','long','-5000','5000','',0,''),(1,14,'short','short','-20','20','',0,''),(1,15,'byte','byte','0','6','',0,''),(1,16,'boolean','boolean','','','',0,''),(1,17,'float','float','-10.00','15.00','5',0,''),(1,18,'double','double','-15.00000','10.00000','5',0,''),(1,19,'char','char','65','90','',0,''),(1,20,'java.math.BigDecimal','BigDecimal','-10000000.000','100000000.000','3',1,''),(1,21,'java.math.BigInteger','BigInteger','-1000000','1000000','',0,''),(1,22,'java.lang.Enum','Enum','0','7','',0,'Lorem, Ipsum, is, simply, dummy, text, of, the, printing, and, typesetting, industry'),(2,1,'java.lang.Integer','Integer','0','150','',1,''),(2,2,'java.lang.Long','Long','0','200','',1,''),(2,3,'java.lang.Short','Short','0','65','',1,''),(2,4,'java.lang.Byte','Byte','0','15','',1,''),(2,5,'java.lang.Boolean','Boolean','','','',1,''),(2,6,'java.lang.Double','Double','100.00000','500.00000','5',1,''),(2,7,'java.lang.Float','Float','0.000','120.000','5',1,''),(2,8,'java.lang.Character','Character','65','90','',1,''),(2,9,'java.lang.String','name','','','',1,''),(2,10,'java.lang.String','email','','','',1,''),(2,11,'java.util.Date','date','1900/01/01 00:00:00','2020/01/01 00:00:00','',1,''),(2,12,'int','int','0','6000','',0,''),(2,13,'long','long','-5000','5000','',0,''),(2,14,'short','short','-20','20','',0,''),(2,15,'byte','byte','0','6','',0,''),(2,16,'boolean','boolean','','','',0,''),(2,17,'float','float','-10.00','15.00','5',0,''),(2,18,'double','double','-15.00000','10.00000','5',0,''),(2,19,'char','char','65','90','',0,''),(2,20,'java.math.BigDecimal','BigDecimal','-10000000.000','100000000.000','3',1,''),(2,21,'java.math.BigInteger','BigInteger','-1000000','1000000','',0,''),(2,22,'java.lang.Enum','Enum','0','7','',0,'Lorem, Ipsum, is, simply, dummy, text, of, the, printing, and, typesetting, industry'),(9,1,'java.lang.Integer','Hours','12','122121','5',1,''),(9,2,'java.lang.Integer','Seconds','123123','12312312','5',1,''),(10,1,'java.lang.String','Name','3','34534','5',1,''),(10,2,'java.lang.String','LastName','657','56756','5',1,''),(11,1,'float','price','54.00001','433.99999','5',1,''),(12,1,'java.util.Date','birthday','1950/06/09 20:00','2017/01/01 20:00','5',1,''),(12,2,'java.lang.Enum','Sex','0','1','5',1,'Male, Female'),(13,1,'boolean','updated','12','435','5',1,''),(13,2,'java.lang.Character','middlename','65','100','5',1,'');
/*!40000 ALTER TABLE `random_data_generation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `random_data_generation_model`
--

DROP TABLE IF EXISTS `random_data_generation_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `random_data_generation_model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_name` varchar(30) NOT NULL,
  `app_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_random_data_generation_model_app_user1_idx` (`app_user_id`),
  CONSTRAINT `fk_random_data_generation_model_app_user1` FOREIGN KEY (`app_user_id`) REFERENCES `app_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `random_data_generation_model`
--

LOCK TABLES `random_data_generation_model` WRITE;
/*!40000 ALTER TABLE `random_data_generation_model` DISABLE KEYS */;
INSERT INTO `random_data_generation_model` VALUES (1,'Default Model',1),(2,'Default Model',2),(9,'First Model',1),(10,'User First Model',3),(11,'User Second Model',3),(12,'testuser First Model',4),(13,'testuser Second Model',4);
/*!40000 ALTER TABLE `random_data_generation_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (2,'ADMIN'),(3,'DBA'),(1,'USER');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancy`
--

DROP TABLE IF EXISTS `vacancy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacancy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vacancy_name` varchar(30) NOT NULL,
  `vacancy_code` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancy`
--

LOCK TABLES `vacancy` WRITE;
/*!40000 ALTER TABLE `vacancy` DISABLE KEYS */;
INSERT INTO `vacancy` VALUES (4,'Susie S Timbrook','a72832f8-2e55-42fe-b4d4-93734ed09b0a'),(5,'Dianne Z Vaught','887b429a-90f7-40fb-9906-abd34c4a25c3'),(6,'Doug K Taylor','3b4ce35a-e17b-401f-9e40-db70e954e6ed');
/*!40000 ALTER TABLE `vacancy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancy_applicant`
--

DROP TABLE IF EXISTS `vacancy_applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vacancy_applicant` (
  `vacancy_id` bigint(20) NOT NULL,
  `applicant_id` bigint(20) NOT NULL,
  PRIMARY KEY (`vacancy_id`,`applicant_id`),
  KEY `FK_APPLICANT` (`applicant_id`),
  CONSTRAINT `FK_APPLICANT` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`id`),
  CONSTRAINT `FK_VACANCY` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancy_applicant`
--

LOCK TABLES `vacancy_applicant` WRITE;
/*!40000 ALTER TABLE `vacancy_applicant` DISABLE KEYS */;
INSERT INTO `vacancy_applicant` VALUES (4,11),(4,12),(5,12),(6,12),(5,13),(6,13),(4,14),(6,14),(4,15),(6,15),(5,16),(6,16),(4,17),(6,17),(4,18),(5,18),(5,19),(4,20);
/*!40000 ALTER TABLE `vacancy_applicant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-09 19:38:10
