/*
SQLyog v10.2 
MySQL - 5.6.21-log : Database - cws
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cws` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cws`;

/*Table structure for table `carlocks` */

DROP TABLE IF EXISTS `carlocks`;

CREATE TABLE `carlocks` (
  `S_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `U_ID` int(10) unsigned DEFAULT NULL,
  `P_ID` int(10) unsigned DEFAULT NULL,
  `IS_RENT` tinyint(1) DEFAULT NULL,
  `USE_STAT` tinyint(1) DEFAULT NULL,
  `FAULT_STAT` tinyint(4) DEFAULT NULL,
  `BLUE_ID` varchar(64) DEFAULT NULL,
  `BULE_PWD` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `carlocks` */

insert  into `carlocks`(`S_ID`,`U_ID`,`P_ID`,`IS_RENT`,`USE_STAT`,`FAULT_STAT`,`BLUE_ID`,`BULE_PWD`) values (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,2,1,1,1,'1','1');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `O_ID` varchar(128) NOT NULL,
  `START_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `END_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `TOTAL_DAY_TIME` int(10) unsigned DEFAULT NULL,
  `TOTAL_NIGHT_TIME` int(10) unsigned DEFAULT NULL,
  `TOTAL_COST` double DEFAULT NULL,
  `DISCOUNT` float DEFAULT NULL,
  `STAT` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`O_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*Table structure for table `orders_associated` */

DROP TABLE IF EXISTS `orders_associated`;

CREATE TABLE `orders_associated` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `U_ID` int(10) unsigned DEFAULT NULL,
  `O_ID` varchar(128) NOT NULL,
  `P_ID` int(10) unsigned DEFAULT NULL,
  `S_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`,`O_ID`),
  KEY `ORDER_ASSOCIATED` (`O_ID`),
  CONSTRAINT `ORDER_ASSOCIATED` FOREIGN KEY (`O_ID`) REFERENCES `orders` (`O_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders_associated` */

/*Table structure for table `parks` */

DROP TABLE IF EXISTS `parks`;

CREATE TABLE `parks` (
  `P_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '停车场id',
  `P_NAME` varchar(64) DEFAULT NULL COMMENT '停车场名',
  `LNG` double DEFAULT NULL COMMENT '经度',
  `LAT` double DEFAULT NULL COMMENT '纬度',
  `PIC` varchar(64) DEFAULT NULL COMMENT '图片url',
  `CITY` varchar(64) DEFAULT NULL COMMENT '城市',
  `AREA` varchar(255) DEFAULT NULL COMMENT '区域',
  `DAY_PRICE` float DEFAULT NULL COMMENT '日价格',
  `NIGHT_PRICE` float DEFAULT NULL COMMENT '夜价格',
  PRIMARY KEY (`P_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=123126 DEFAULT CHARSET=utf8;

/*Data for the table `parks` */

insert  into `parks`(`P_ID`,`P_NAME`,`LNG`,`LAT`,`PIC`,`CITY`,`AREA`,`DAY_PRICE`,`NIGHT_PRICE`) values (1,'test',132.1,15.3,'','china','test',1.1,2.2),(2,'test',132.1,15.3,'ttt','china','test',1.1,2.2),(3,'test',132.1,15.3,'ttt','china','test',1.1,2.2),(123124,'test2',132.1,15.3,'ttt','china','test',1.1,2.2),(123125,'test2',132.1,15.3,'ttt','china','test',1.1,2.2);

/*Table structure for table `parks_comment` */

DROP TABLE IF EXISTS `parks_comment`;

CREATE TABLE `parks_comment` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `P_ID` int(10) unsigned NOT NULL,
  `U_ID` int(10) unsigned NOT NULL,
  `CONTENT` varchar(600) DEFAULT NULL,
  `STAR` tinyint(4) DEFAULT NULL,
  `TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `USER_COMMENT` (`U_ID`),
  KEY `PARK_COMMENT` (`P_ID`),
  CONSTRAINT `PARK_COMMENT` FOREIGN KEY (`P_ID`) REFERENCES `parks` (`P_ID`) ON DELETE CASCADE,
  CONSTRAINT `USER_COMMENT` FOREIGN KEY (`U_ID`) REFERENCES `users` (`U_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `parks_comment` */

/*Table structure for table `parks_space` */

DROP TABLE IF EXISTS `parks_space`;

CREATE TABLE `parks_space` (
  `P_ID` int(10) unsigned NOT NULL,
  `FREENUM` int(11) DEFAULT NULL,
  `ALLNUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`P_ID`),
  CONSTRAINT `PARK_SPACE` FOREIGN KEY (`P_ID`) REFERENCES `parks` (`P_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `parks_space` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `U_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `U_NAME` varchar(16) NOT NULL COMMENT '用户名',
  `PWD` varchar(128) NOT NULL COMMENT '密码',
  `PHONE` varchar(11) NOT NULL COMMENT '手机号',
  `CITY` varchar(64) DEFAULT NULL COMMENT '城市',
  `PIC` varchar(128) DEFAULT NULL COMMENT '头像',
  `WEIXIN_ID` varchar(64) DEFAULT NULL COMMENT '微信',
  `WEIBO_ID` varchar(64) DEFAULT NULL COMMENT '微博',
  `ZHIFUBAO_ID` varchar(64) DEFAULT NULL COMMENT '支付宝',
  `CAR_ID` varchar(16) DEFAULT NULL COMMENT '车牌号',
  `LEVEL` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户等级',
  `REG_TIME` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `LASTLOGIN_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`U_ID`,`U_NAME`,`PWD`,`PHONE`,`CITY`,`PIC`,`WEIXIN_ID`,`WEIBO_ID`,`ZHIFUBAO_ID`,`CAR_ID`,`LEVEL`,`REG_TIME`,`LASTLOGIN_TIME`) values (2,'','','1111',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'2016-05-05 14:13:00'),(3,'1520****032','123dasda!@#!@#','15201316032',NULL,NULL,NULL,NULL,NULL,NULL,1,'2016-05-05 14:53:24','2016-05-05 14:53:24'),(4,'1520****033','123dasd???>>>|\":','15201316033',NULL,NULL,NULL,NULL,NULL,NULL,1,'2016-05-05 14:59:12','2016-05-05 14:59:12'),(5,'1520****034','4076A08EF1C497BD8AB5BC2B73FC1D0C','15201316034',NULL,NULL,NULL,NULL,NULL,NULL,1,'2016-05-05 15:15:48','2016-05-05 15:15:48');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
