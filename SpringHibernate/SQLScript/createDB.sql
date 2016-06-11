use db;
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
) ENGINE=InnoDB;

CREATE TABLE `random_data_generation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `basic_class_constants` varchar(30) NOT NULL,
  `property_name` varchar(30) NOT NULL,
  `obj_min` varchar(50) NOT NULL,
  `obj_max` varchar(50) NOT NULL,
  `obj_precision` varchar(30) NOT NULL,
  `b_allow_nulls` tinyint(1) DEFAULT NULL,
  `obj_enum` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `vacancy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vacancy_name` varchar(30) NOT NULL,
  `vacancy_code` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `vacancy_applicant` (
  `vacancy_id` bigint(20) NOT NULL,
  `applicant_id` bigint(20) NOT NULL,
  PRIMARY KEY (`vacancy_id`,`applicant_id`),
  KEY `FK_APPLICANT` (`applicant_id`),
  CONSTRAINT `FK_APPLICANT` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`id`),
  CONSTRAINT `FK_VACANCY` FOREIGN KEY (`vacancy_id`) REFERENCES `vacancy` (`id`)
) ENGINE=InnoDB;


INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (1,'java.lang.Integer','Integer','0','150','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (2,'java.lang.Long','Long','0','200','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (3,'java.lang.Short','Short','0','65','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (4,'java.lang.Byte','Byte','0','15','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (5,'java.lang.Boolean','Boolean','','','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (6,'java.lang.Double','Double','100.00000','500.00000','5',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (7,'java.lang.Float','Float','0.000','120.000','5',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (8,'java.lang.Character','Character','65','90','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (9,'java.lang.String','name','','','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (10,'java.lang.String','email','','','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (11,'java.util.Date','date','1900/01/01 00:00:00','2020/01/01 00:00:00','',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (12,'int','int','0','6000','',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (13,'long','long','-5000','5000','',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (14,'short','short','-20','20','',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (15,'byte','byte','0','6','',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (16,'boolean','boolean','','','',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (17,'float','float','-10.00','15.00','5',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (18,'double','double','-15.00000','10.00000','5',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (19,'char','char','65','90','',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (20,'java.math.BigDecimal','BigDecimal','-10000000.000','100000000.000','3',1,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (21,'java.math.BigInteger','BigInteger','-1000000','1000000','',0,'');
INSERT INTO `random_data_generation` (`id`,`basic_class_constants`,`property_name`,`obj_min`,`obj_max`,`obj_precision`,`b_allow_nulls`,`obj_enum`) VALUES (22,'java.lang.Enum','Enum','0','7','',0,'Lorem, Ipsum, is, simply, dummy, text, of, the, printing, and, typesetting, industry');
