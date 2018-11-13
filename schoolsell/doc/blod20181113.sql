-- MySQL dump 10.13  Distrib 8.0.13, for Linux (x86_64)
--
-- Host: localhost    Database: schoolsell
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_user` varchar(32) NOT NULL COMMENT '管理员名称',
  `admin_pwd` varchar(32) NOT NULL COMMENT '管理员密码',
  `token` varchar(32) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_user_UNIQUE` (`admin_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','pwd','1533780173275393163','2018-08-03 02:43:05','2018-08-09 02:02:53'),(3,'test','test','1542072283725817233','2018-08-06 15:27:02','2018-11-13 01:24:43');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_info`
--

DROP TABLE IF EXISTS `business_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `business_info` (
  `business_id` varchar(32) NOT NULL COMMENT '商家ID',
  `business_name` varchar(32) NOT NULL COMMENT '商家名',
  `business_phone` varchar(32) NOT NULL,
  `business_pwd` varchar(32) NOT NULL COMMENT '商家密码',
  `shop_id` varchar(32) DEFAULT NULL COMMENT '店铺ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`business_id`),
  UNIQUE KEY `business_phone_UNIQUE` (`business_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_info`
--

LOCK TABLES `business_info` WRITE;
/*!40000 ALTER TABLE `business_info` DISABLE KEYS */;
INSERT INTO `business_info` VALUES ('109f418e375c43798e389c732a874eac','辣么香麻辣烫','131','131','626eaf6000804a4095a5397ce9c7bae1','2018-09-12 07:58:47','2018-10-20 07:18:43'),('18ff7e5b70a14320b417ce8cf71702e1','爱鱼坊','131231231','131231231','a4bf121b9bac431fa5f64ec95c7164b4','2018-08-23 05:42:37','2018-10-20 07:40:00'),('2c0464b33d124f678ce5b2b9b9ba93a6','番食社','122312312','1536215974352397600','2fb35c5764c74f9d85b71339965f4b6c','2018-09-06 06:39:34','2018-10-20 07:36:38'),('2c973f295f0545baa9d11aad3814df12','广东肠粉','1310000000','1536215717804601010','87e90a7a7e3b4f2fa9626439ad553e14','2018-09-06 06:35:17','2018-09-12 07:50:50'),('6612239295434740ae03c4113ba2ec04','沙县小吃','123','123','ae1ffee24d244066b5cb72224ed33fb5','2018-08-23 02:51:54','2018-09-25 23:56:32'),('89da9d3b7b7847b1a0b52f376f6d11f9','南北风味小吃','151','151','ddf55aedd69c42e2a6eec643d19e2289','2018-08-23 02:38:16','2018-10-10 02:05:03'),('b751d8a2d70e44fa8b88c6a1b098f73e','豪客来','123123123','1536215911666373143','6c58a8bb49bb40eea2273ed6f6a572ca','2018-09-06 06:38:31','2018-10-20 07:37:12'),('bbf128284d804b9fa74ea2dac255ca42','快餐','15611','15611',NULL,'2018-11-10 08:19:45','2018-11-10 08:19:45');
/*!40000 ALTER TABLE `business_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL COMMENT '订单ID',
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `product_name` varchar(32) NOT NULL COMMENT '商品名',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_img` varchar(255) NOT NULL COMMENT '商品图片地址',
  `product_amount` decimal(8,2) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES ('1541423700755222689','ae1ffee24d244066b5cb72224ed33fb5','1541423700755949079','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:00','2018-11-05 13:15:00'),('1541423700755396245','ae1ffee24d244066b5cb72224ed33fb5','1541423700755949079','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:00','2018-11-05 13:15:00'),('1541423700755767767','ae1ffee24d244066b5cb72224ed33fb5','1541423700755949079','d6f9417f31b942bcbc8b9287acbf136f','番茄鸡蛋盖浇饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:00','2018-11-05 13:15:00'),('1541423708221327546','ae1ffee24d244066b5cb72224ed33fb5','1541423708221587328','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:08','2018-11-05 13:15:08'),('1541423708221882616','ae1ffee24d244066b5cb72224ed33fb5','1541423708221587328','5e0e8b65f5a146a7826a8991a5f35d6b','肉丝煮面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:08','2018-11-05 13:15:08'),('1541423708221975527','ae1ffee24d244066b5cb72224ed33fb5','1541423708221587328','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:08','2018-11-05 13:15:08'),('1541423727611687355','ae1ffee24d244066b5cb72224ed33fb5','1541423727611271684','2164776f008447168848ea51116af83c','西红柿蛋汤',5.00,1,'https://sell.nos-eastchina1.126.net/d87b7902ef284302b132cf7a903005f4.jpg',5.00,'2018-11-05 13:15:27','2018-11-05 13:15:27'),('1541423732238445158','ae1ffee24d244066b5cb72224ed33fb5','1541423732238268209','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:32','2018-11-05 13:15:32'),('1541423736549113577','ae1ffee24d244066b5cb72224ed33fb5','1541423736549760366','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:15:36','2018-11-05 13:15:36'),('1541424199518164212','626eaf6000804a4095a5397ce9c7bae1','1541424199518805930','4ac1ea65ed8246a59c30d494510a9a72','牛肉煮面',10.00,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',10.00,'2018-11-05 13:23:19','2018-11-05 13:23:19'),('1541424482694208314','ae1ffee24d244066b5cb72224ed33fb5','1541424482694618826','2bb37009b6644577913fab615e94772e','鸡蛋炒饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-05 13:28:02','2018-11-05 13:28:02'),('1541472118569344719','ae1ffee24d244066b5cb72224ed33fb5','1541472118569805196','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-06 02:41:59','2018-11-06 02:41:59'),('1541472118569473723','ae1ffee24d244066b5cb72224ed33fb5','1541472118569805196','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-06 02:41:59','2018-11-06 02:41:59'),('1541472118569529925','ae1ffee24d244066b5cb72224ed33fb5','1541472118569805196','2164776f008447168848ea51116af83c','西红柿蛋汤',5.00,1,'https://sell.nos-eastchina1.126.net/d87b7902ef284302b132cf7a903005f4.jpg',5.00,'2018-11-06 02:41:59','2018-11-06 02:41:59'),('1541509894214383394','ae1ffee24d244066b5cb72224ed33fb5','1541509894213260944','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,2,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',17.00,'2018-11-06 13:11:34','2018-11-06 13:11:34'),('1541509894214703110','ae1ffee24d244066b5cb72224ed33fb5','1541509894213260944','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,2,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',17.00,'2018-11-06 13:11:34','2018-11-06 13:11:34'),('1541509894214870893','ae1ffee24d244066b5cb72224ed33fb5','1541509894213260944','2164776f008447168848ea51116af83c','西红柿蛋汤',5.00,1,'https://sell.nos-eastchina1.126.net/d87b7902ef284302b132cf7a903005f4.jpg',5.00,'2018-11-06 13:11:34','2018-11-06 13:11:34'),('1541513800890772257','ae1ffee24d244066b5cb72224ed33fb5','1541513800890216018','2bb37009b6644577913fab615e94772e','鸡蛋炒饭',8.50,15,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',127.50,'2018-11-06 14:16:40','2018-11-06 14:16:40'),('1541513889903117738','ae1ffee24d244066b5cb72224ed33fb5','1541513889902663671','d6f9417f31b942bcbc8b9287acbf136f','番茄鸡蛋盖浇饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-06 14:18:09','2018-11-06 14:18:09'),('1541513889903275757','ae1ffee24d244066b5cb72224ed33fb5','1541513889902663671','5e0e8b65f5a146a7826a8991a5f35d6b','肉丝煮面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-06 14:18:09','2018-11-06 14:18:09'),('1541513889903980630','ae1ffee24d244066b5cb72224ed33fb5','1541513889902663671','65257bce644b4d4d953202303ac768d0','肉丝盖浇饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-06 14:18:09','2018-11-06 14:18:09'),('1541560456607568628','ae1ffee24d244066b5cb72224ed33fb5','1541560456607259361','d6f9417f31b942bcbc8b9287acbf136f','番茄鸡蛋盖浇饭',8.50,2,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',17.00,'2018-11-07 03:14:16','2018-11-07 03:14:16'),('1541560456607931520','ae1ffee24d244066b5cb72224ed33fb5','1541560456607259361','65257bce644b4d4d953202303ac768d0','肉丝盖浇饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-07 03:14:16','2018-11-07 03:14:16'),('1541564381601106354','ae1ffee24d244066b5cb72224ed33fb5','1541564381601851086','d6f9417f31b942bcbc8b9287acbf136f','番茄鸡蛋盖浇饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-07 04:19:41','2018-11-07 04:19:41'),('1541564381601470986','ae1ffee24d244066b5cb72224ed33fb5','1541564381601851086','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-07 04:19:41','2018-11-07 04:19:41'),('1541564381601532775','ae1ffee24d244066b5cb72224ed33fb5','1541564381601851086','65257bce644b4d4d953202303ac768d0','肉丝盖浇饭',8.50,2,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',17.00,'2018-11-07 04:19:41','2018-11-07 04:19:41'),('1541564381601707384','ae1ffee24d244066b5cb72224ed33fb5','1541564381601851086','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-07 04:19:41','2018-11-07 04:19:41'),('1541985218164204697','ae1ffee24d244066b5cb72224ed33fb5','1541985218163766780','65257bce644b4d4d953202303ac768d0','肉丝盖浇饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-11 08:48:41','2018-11-11 08:48:41'),('1541985218164334748','ae1ffee24d244066b5cb72224ed33fb5','1541985218163766780','d6f9417f31b942bcbc8b9287acbf136f','番茄鸡蛋盖浇饭',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-11 08:48:41','2018-11-11 08:48:41'),('1541985386288175617','ae1ffee24d244066b5cb72224ed33fb5','1541985386288176167','2164776f008447168848ea51116af83c','西红柿蛋汤',5.00,1,'https://sell.nos-eastchina1.126.net/f5dd31dbc70f41e4b4d0f5014f5998a0.jpg',5.00,'2018-11-11 08:51:29','2018-11-11 08:51:29'),('1541985386288524867','ae1ffee24d244066b5cb72224ed33fb5','1541985386288176167','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,1,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',8.50,'2018-11-11 08:51:29','2018-11-11 08:51:29'),('1541985386288935239','ae1ffee24d244066b5cb72224ed33fb5','1541985386288176167','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,1,'https://sell.nos-eastchina1.126.net/1841623990ab48d58eda23412f36a87b.jpg',8.50,'2018-11-11 08:51:29','2018-11-11 08:51:29'),('1541986267004237399','ae1ffee24d244066b5cb72224ed33fb5','1541986267004726331','2164776f008447168848ea51116af83c','西红柿蛋汤',5.00,1,'https://sell.nos-eastchina1.126.net/f5dd31dbc70f41e4b4d0f5014f5998a0.jpg',5.00,'2018-11-12 01:31:07','2018-11-12 01:31:07'),('1541986267004477055','ae1ffee24d244066b5cb72224ed33fb5','1541986267004726331','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',8.50,2,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',17.00,'2018-11-12 01:31:07','2018-11-12 01:31:07'),('1541986267004888693','ae1ffee24d244066b5cb72224ed33fb5','1541986267004726331','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',8.50,3,'https://sell.nos-eastchina1.126.net/1841623990ab48d58eda23412f36a87b.jpg',25.50,'2018-11-12 01:31:07','2018-11-12 01:31:07');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_master`
--

DROP TABLE IF EXISTS `order_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL COMMENT '店铺ID',
  `order_name` varchar(32) NOT NULL COMMENT '店铺名',
  `user_id` varchar(45) NOT NULL COMMENT '买家名',
  `username` varchar(32) NOT NULL,
  `phone` varchar(16) NOT NULL COMMENT '买家电话',
  `address` varchar(45) NOT NULL COMMENT '买家地址',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` int(11) NOT NULL COMMENT '订单状态',
  `order_message` varchar(45) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_master`
--

LOCK TABLES `order_master` WRITE;
/*!40000 ALTER TABLE `order_master` DISABLE KEYS */;
INSERT INTO `order_master` VALUES ('1541423700755949079','ae1ffee24d244066b5cb72224ed33fb5','肉丝炒面等3件商品','2f11f71643f14a769adc67351c996626','test','131','2504',25.50,2,NULL,'2018-11-05 13:15:00','2018-11-10 15:03:39'),('1541423708221587328','ae1ffee24d244066b5cb72224ed33fb5','肉丝煮面等3件商品','2f11f71643f14a769adc67351c996626','test','131','2504',25.50,1,NULL,'2018-11-05 13:15:08','2018-11-12 08:17:54'),('1541423727611271684','ae1ffee24d244066b5cb72224ed33fb5','西红柿蛋汤等1件商品','2f11f71643f14a769adc67351c996626','test','131','2504',5.00,2,NULL,'2018-11-05 13:15:27','2018-11-10 15:04:20'),('1541423732238268209','ae1ffee24d244066b5cb72224ed33fb5','肉丝炒面等1件商品','2f11f71643f14a769adc67351c996626','test','131','2504',8.50,1,NULL,'2018-11-05 13:15:32','2018-11-12 08:17:29'),('1541423736549760366','ae1ffee24d244066b5cb72224ed33fb5','三鲜炒面等1件商品','2f11f71643f14a769adc67351c996626','test','131','2504',8.50,1,'','2018-11-05 13:15:36','2018-11-12 08:17:29'),('1541424199518805930','626eaf6000804a4095a5397ce9c7bae1','牛肉煮面等1件商品','2f11f71643f14a769adc67351c996626','test','131','2504',10.00,2,NULL,'2018-11-05 13:23:19','2018-11-06 14:09:51'),('1541424482694618826','ae1ffee24d244066b5cb72224ed33fb5','鸡蛋炒饭等1件商品','2f11f71643f14a769adc67351c996626','test','131','2504',8.50,2,'','2018-11-05 13:28:02','2018-11-06 02:40:41'),('1541472118569805196','ae1ffee24d244066b5cb72224ed33fb5','西红柿蛋汤等3件商品','2f11f71643f14a769adc67351c996626','test','131','2504',22.00,1,'加辣','2018-11-06 02:41:58','2018-11-06 14:09:33'),('1541509894213260944','ae1ffee24d244066b5cb72224ed33fb5','西红柿蛋汤等3件商品','2f11f71643f14a769adc67351c996626','test','131','2504',39.00,1,'不要辣','2018-11-06 13:11:34','2018-11-12 08:17:54'),('1541513800890216018','ae1ffee24d244066b5cb72224ed33fb5','鸡蛋炒饭等1件商品','2f11f71643f14a769adc67351c996626','test','131','2504',127.50,1,'','2018-11-06 14:16:40','2018-11-06 14:16:51'),('1541513889902663671','ae1ffee24d244066b5cb72224ed33fb5','肉丝煮面等3件商品','2f11f71643f14a769adc67351c996626','test','131','2504',25.50,1,'','2018-11-06 14:18:09','2018-11-12 08:17:54'),('1541560456607259361','ae1ffee24d244066b5cb72224ed33fb5','肉丝盖浇饭等2件商品','2f11f71643f14a769adc67351c996626','test','131','2504',25.50,1,'加辣','2018-11-07 03:14:16','2018-11-07 03:14:43'),('1541564381601851086','ae1ffee24d244066b5cb72224ed33fb5','肉丝盖浇饭等4件商品','f06fc6bb56d043d0bb07fb37d43122c2','springboot','15600','250',42.50,1,'','2018-11-07 04:19:41','2018-11-08 03:35:37'),('1541985218163766780','ae1ffee24d244066b5cb72224ed33fb5','肉丝盖浇饭等2件商品','2f11f71643f14a769adc67351c996626','test','131','2504',17.00,1,'','2018-11-11 08:48:41','2018-11-11 08:48:43'),('1541985386288176167','ae1ffee24d244066b5cb72224ed33fb5','西红柿蛋汤等3件商品','2f11f71643f14a769adc67351c996626','test','131','2504',22.00,1,'','2018-11-11 08:51:29','2018-11-11 08:51:30'),('1541986267004726331','ae1ffee24d244066b5cb72224ed33fb5','西红柿蛋汤等3件商品','2f11f71643f14a769adc67351c996626','test','131','2504',47.50,1,'','2018-11-12 01:31:07','2018-11-12 02:37:50');
/*!40000 ALTER TABLE `order_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_category` (
  `category_id` varchar(32) NOT NULL,
  `category_name` varchar(32) NOT NULL COMMENT '分类名',
  `shop_id` varchar(32) NOT NULL COMMENT '店铺ID',
  `category_type` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES ('1208fdb85cdf454e899ecbaa609852f7','汤类','ae1ffee24d244066b5cb72224ed33fb5',5,'2018-09-29 02:22:06','2018-09-29 02:22:06'),('4667af06f15447638b5c7cb323995a9a','炒面','ae1ffee24d244066b5cb72224ed33fb5',1,'2018-09-27 08:54:29','2018-09-27 08:54:29'),('666f4aa4ac8047338e95571d0a93cd6d','盖浇饭','ae1ffee24d244066b5cb72224ed33fb5',2,'2018-09-27 09:30:01','2018-09-27 09:30:11'),('a9f3e119aff74bbd97201ba4d143e38d','煮面','ae1ffee24d244066b5cb72224ed33fb5',3,'2018-09-28 09:50:37','2018-09-28 09:50:37'),('b0cf43aad9ff455c81c0ebafa60a49c6','炒饭','ae1ffee24d244066b5cb72224ed33fb5',4,'2018-09-29 02:21:28','2018-09-29 02:21:28'),('d4df9682661349aab14957387fe6a6a5','煮面','626eaf6000804a4095a5397ce9c7bae1',1,'2018-11-05 07:30:41','2018-11-05 07:30:41');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_info`
--

DROP TABLE IF EXISTS `product_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(32) NOT NULL COMMENT '商品名',
  `category_id` varchar(32) NOT NULL COMMENT '分类ID',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `shop_id` varchar(32) NOT NULL COMMENT '商铺ID',
  `product_img` varchar(255) DEFAULT NULL COMMENT '商品图片地址',
  `product_stock` int(11) DEFAULT NULL COMMENT '商品库存',
  `product_status` int(11) NOT NULL COMMENT '商品状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_info`
--

LOCK TABLES `product_info` WRITE;
/*!40000 ALTER TABLE `product_info` DISABLE KEYS */;
INSERT INTO `product_info` VALUES ('2164776f008447168848ea51116af83c','西红柿蛋汤','1208fdb85cdf454e899ecbaa609852f7',5.00,'ae1ffee24d244066b5cb72224ed33fb5','https://sell.nos-eastchina1.126.net/f5dd31dbc70f41e4b4d0f5014f5998a0.jpg',NULL,1,'2018-11-01 01:27:59','2018-11-08 01:23:40'),('2bb37009b6644577913fab615e94772e','鸡蛋炒饭','b0cf43aad9ff455c81c0ebafa60a49c6',8.50,'ae1ffee24d244066b5cb72224ed33fb5','https://sell.nos-eastchina1.126.net/edc6b61d75a1426e97729e5d544ab629.jpg',NULL,1,'2018-11-01 02:09:47','2018-11-08 01:23:49'),('4ac1ea65ed8246a59c30d494510a9a72','牛肉煮面','d4df9682661349aab14957387fe6a6a5',10.00,'626eaf6000804a4095a5397ce9c7bae1','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,1,'2018-11-05 07:31:07','2018-11-05 07:39:16'),('5e0e8b65f5a146a7826a8991a5f35d6b','肉丝煮面','a9f3e119aff74bbd97201ba4d143e38d',8.50,'ae1ffee24d244066b5cb72224ed33fb5','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,1,'2018-11-01 02:09:07','2018-11-01 02:09:12'),('65257bce644b4d4d953202303ac768d0','肉丝盖浇饭','666f4aa4ac8047338e95571d0a93cd6d',8.50,'ae1ffee24d244066b5cb72224ed33fb5','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,1,'2018-10-31 11:45:52','2018-10-31 11:50:48'),('9003ac84f2334279b4f8f7c49eb82149','肉丝炒面','4667af06f15447638b5c7cb323995a9a',8.50,'ae1ffee24d244066b5cb72224ed33fb5','https://sell.nos-eastchina1.126.net/1841623990ab48d58eda23412f36a87b.jpg',100,1,'2018-09-29 02:20:03','2018-11-08 06:14:15'),('b568fea3e1184191bdeb40eb0e16221a','三鲜炒面','4667af06f15447638b5c7cb323995a9a',8.50,'ae1ffee24d244066b5cb72224ed33fb5','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,1,'2018-10-31 11:41:08','2018-10-31 11:50:49'),('d6f9417f31b942bcbc8b9287acbf136f','番茄鸡蛋盖浇饭','666f4aa4ac8047338e95571d0a93cd6d',8.50,'ae1ffee24d244066b5cb72224ed33fb5','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',100,1,'2018-09-29 01:34:59','2018-10-31 11:39:50');
/*!40000 ALTER TABLE `product_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region_category`
--

DROP TABLE IF EXISTS `region_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `region_category` (
  `region_id` varchar(32) NOT NULL COMMENT '区域分类ID',
  `region_name` varchar(32) NOT NULL COMMENT '区域分类名',
  `region_type` int(11) NOT NULL COMMENT '区域编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region_category`
--

LOCK TABLES `region_category` WRITE;
/*!40000 ALTER TABLE `region_category` DISABLE KEYS */;
INSERT INTO `region_category` VALUES ('21fdaad9d60549518ccd7b84073e55aa','一号楼一层',3,'2018-08-20 03:55:22','2018-10-19 10:22:53'),('dcd6209fba09482388b33f32ffba7749','食堂二楼',2,'2018-08-20 03:54:55','2018-08-20 03:54:55'),('eda576589ab34980ae7bbe1855bce36b','食堂一楼',1,'2018-08-20 03:54:35','2018-08-20 03:54:35');
/*!40000 ALTER TABLE `region_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shop` (
  `shop_id` varchar(32) NOT NULL,
  `shop_name` varchar(32) NOT NULL COMMENT '商铺名',
  `business_id` varchar(32) NOT NULL COMMENT '商家ID',
  `region_id` varchar(32) NOT NULL COMMENT '所属区域ID',
  `shop_logo` varchar(255) NOT NULL COMMENT '商铺LOGO',
  `shop_describe` varchar(255) DEFAULT NULL COMMENT '商铺描述',
  `shop_addr` varchar(32) NOT NULL COMMENT '商铺地址',
  `shop_phone` varchar(16) NOT NULL COMMENT '商家电话',
  `shop_status` int(11) NOT NULL COMMENT '营业状态',
  `shop_hour` varchar(45) DEFAULT NULL COMMENT '营业时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`shop_id`),
  UNIQUE KEY `shop_phone_UNIQUE` (`shop_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES ('2fb35c5764c74f9d85b71339965f4b6c','番食社','2c0464b33d124f678ce5b2b9b9ba93a6','21fdaad9d60549518ccd7b84073e55aa','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,'一号楼4号铺','122312312',1,NULL,'2018-10-20 07:36:38','2018-10-24 11:27:59'),('626eaf6000804a4095a5397ce9c7bae1','辣么香麻辣烫','109f418e375c43798e389c732a874eac','21fdaad9d60549518ccd7b84073e55aa','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,'食堂二楼2号铺','131',1,NULL,'2018-10-20 07:18:43','2018-10-24 11:27:59'),('6c58a8bb49bb40eea2273ed6f6a572ca','豪客来','b751d8a2d70e44fa8b88c6a1b098f73e','21fdaad9d60549518ccd7b84073e55aa','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,'一号楼3号铺','123123123',1,NULL,'2018-10-20 07:37:12','2018-10-24 11:27:59'),('87e90a7a7e3b4f2fa9626439ad553e14','广式美味肠粉','2c973f295f0545baa9d11aad3814df12','dcd6209fba09482388b33f32ffba7749','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg',NULL,'食堂二号楼3号铺','1310000000',1,NULL,'2018-09-06 06:36:17','2018-10-24 11:27:59'),('a4bf121b9bac431fa5f64ec95c7164b4','爱鱼坊','18ff7e5b70a14320b417ce8cf71702e1','eda576589ab34980ae7bbe1855bce36b','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','','一号楼2号','131231231',1,'00:59-12:59','2018-08-24 03:51:19','2018-10-24 11:27:59'),('ae1ffee24d244066b5cb72224ed33fb5','沙县小吃','6612239295434740ae03c4113ba2ec04','21fdaad9d60549518ccd7b84073e55aa','https://sell.nos-eastchina1.126.net/482257825d1946d6bc766640b22ad4a6.jpg','123','一号楼中间','123',1,'08:00-20:00','2018-08-23 05:35:26','2018-11-01 01:27:25'),('ddf55aedd69c42e2a6eec643d19e2289','南北风味小吃','89da9d3b7b7847b1a0b52f376f6d11f9','dcd6209fba09482388b33f32ffba7749','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','好吃的南北风味小吃','食堂二楼中间','1511111',1,'08:00-19:00','2018-08-23 05:39:27','2018-10-24 11:28:10');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_sale`
--

DROP TABLE IF EXISTS `shop_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `shop_sale` (
  `sale_id` varchar(32) NOT NULL,
  `shop_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `product_name` varchar(32) NOT NULL COMMENT '商品名称',
  `sale_num` int(11) NOT NULL COMMENT '销量',
  `turnover` decimal(8,2) NOT NULL COMMENT '营业总额',
  `sale_time` date NOT NULL COMMENT '销售日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_sale`
--

LOCK TABLES `shop_sale` WRITE;
/*!40000 ALTER TABLE `shop_sale` DISABLE KEYS */;
INSERT INTO `shop_sale` VALUES ('00f061ee43a14f5fbc208c8c848e1efe','ae1ffee24d244066b5cb72224ed33fb5','2164776f008447168848ea51116af83c','西红柿蛋汤',1,5.00,'2018-11-05','2018-11-08 08:32:35','2018-11-08 08:32:35'),('1f9e94669b954892b4cf2a9bc7e184ae','ae1ffee24d244066b5cb72224ed33fb5','5e0e8b65f5a146a7826a8991a5f35d6b','肉丝煮面',1,8.50,'2018-11-05','2018-11-08 08:32:35','2018-11-08 08:32:35'),('44ad2b23bfb140758f72948e16eec1fa','ae1ffee24d244066b5cb72224ed33fb5','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',2,17.00,'2018-11-05','2018-11-08 08:32:35','2018-11-08 08:32:35'),('5c737d3876544b42ab4e5fbb76545f08','ae1ffee24d244066b5cb72224ed33fb5','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',1,8.50,'2018-11-06','2018-11-08 08:39:01','2018-11-08 08:39:01'),('6ff79fe064994a9ab3ce94d32951d80a','ae1ffee24d244066b5cb72224ed33fb5','9003ac84f2334279b4f8f7c49eb82149','肉丝炒面',1,8.50,'2018-11-06','2018-11-08 08:39:01','2018-11-08 08:39:01'),('8bbbd7130bee4476a2e2ddf313ab929b','ae1ffee24d244066b5cb72224ed33fb5','2bb37009b6644577913fab615e94772e','鸡蛋炒饭',15,127.50,'2018-11-06','2018-11-08 08:39:01','2018-11-08 08:39:01'),('ae6e86c2148a4359ad10a64ac664511d','ae1ffee24d244066b5cb72224ed33fb5','d6f9417f31b942bcbc8b9287acbf136f','番茄鸡蛋盖浇饭',1,8.50,'2018-11-05','2018-11-08 08:32:35','2018-11-08 08:32:35'),('ba378d2fec1f489b9c76f63c617eb026','ae1ffee24d244066b5cb72224ed33fb5','2164776f008447168848ea51116af83c','西红柿蛋汤',1,5.00,'2018-11-06','2018-11-08 08:39:01','2018-11-08 08:39:01'),('e902a5be96ef40ff920a79f48ad1c958','ae1ffee24d244066b5cb72224ed33fb5','b568fea3e1184191bdeb40eb0e16221a','三鲜炒面',2,17.00,'2018-11-05','2018-11-08 08:32:35','2018-11-08 08:32:35');
/*!40000 ALTER TABLE `shop_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `phone` varchar(16) NOT NULL COMMENT '手机',
  `address` varchar(32) DEFAULT NULL COMMENT '地址',
  `user_img` varchar(255) NOT NULL COMMENT '头像',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('05fe7d1afa95421bb6e0c8473cb484a3','c#','123410423','123410423',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 02:01:16','2018-10-24 11:30:05'),('2760b3dc23e742afb8955242b2a8ee3a','bootstrap','1231234356','1231234356',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 02:00:33','2018-10-24 11:30:05'),('2f11f71643f14a769adc67351c996626','test','131','131','2504','https://sell.nos-eastchina1.126.net/34f2c758d84140d897c517f8e3f3a7e6.jpg','2018-08-05 09:28:45','2018-11-08 12:47:28'),('4c9b094423ae47fc87f92d9c7c498699','apache','1300000000','1300000000',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 01:24:59','2018-10-24 11:30:05'),('680a396021e549c28482b1dd89c62a0e','linux','123312','123312',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 01:23:30','2018-10-24 11:30:05'),('89828b10324b45caa690841f2c0520dd','java','312453125','312453125',NULL,'http://paicrtff0.bkt.clouddn.com/wertweqtertrwetgwedrwer','2018-08-22 01:59:50','2018-08-22 01:59:50'),('952f0d7c070046d1ab9ea9734ece896f','php','123412123','123412123',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 01:59:38','2018-10-24 11:30:05'),('a295efd039fa474eb5f72c4d0eb2c6e7','javascript','1231132123','1231132123',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 02:00:13','2018-10-24 11:30:05'),('b00add5403d84113a54b154cc171d2b8','linux','213123213','213123213',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-10-17 07:37:30','2018-10-24 11:30:05'),('cfb640a5e19649a09124346cae01632a','cheng','cheng','13100000000','2504','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-04 08:13:49','2018-10-24 11:30:05'),('e6cdbd32f30e4d65bcd86b68dc4c37e7','python','15432145','15432145',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 02:00:53','2018-10-24 11:30:05'),('ec14e4baf7284dce8b0be0925fa5fb91','spring','150000000','150000000',NULL,'https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 01:25:30','2018-10-24 11:30:05'),('f06fc6bb56d043d0bb07fb37d43122c2','springboot','15600','15600','250','https://sell.nos-eastchina1.126.net/wertweqtertrwetgwedrwer.jpg','2018-08-22 01:40:38','2018-11-07 04:19:17');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sale`
--

DROP TABLE IF EXISTS `user_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_sale` (
  `sale_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `turnover` decimal(8,2) NOT NULL COMMENT '营业总额',
  `order_num` int(11) NOT NULL,
  `sale_time` date NOT NULL COMMENT '销售日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`sale_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sale`
--

LOCK TABLES `user_sale` WRITE;
/*!40000 ALTER TABLE `user_sale` DISABLE KEYS */;
INSERT INTO `user_sale` VALUES ('10fa32d484264c74a89857c386cc8ba6','2f11f71643f14a769adc67351c996626','test',149.50,2,'2018-11-06','2018-11-13 01:14:16','2018-11-13 01:14:16'),('3a345bcf040041f3bf1269d1119765aa','2f11f71643f14a769adc67351c996626','test',154.50,6,'2018-11-12','2018-11-13 01:17:34','2018-11-13 01:17:34');
/*!40000 ALTER TABLE `user_sale` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-13  9:59:08
