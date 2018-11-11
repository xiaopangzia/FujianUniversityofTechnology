//管理员表
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

//公告板表
CREATE TABLE `billborad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

//鲜花表
CREATE TABLE `flower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `fmes` varchar(45) NOT NULL,
  `fprice` varchar(45) NOT NULL,
  `fnum` int(11) NOT NULL,
  `fimg` varchar(45) NOT NULL,
  `fstatus` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

//用户表
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(45) NOT NULL,
  `upwd` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `address` varchar(45) NOT NULL,
  `sex` varchar(5) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

//订单表
CREATE TABLE `order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `otime` varchar(20) NOT NULL,
  `ototal` varchar(10) NOT NULL,
  `ostatus` int(11) NOT NULL,
  `oaddr` varchar(45) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid_idx` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

//订单条目表
CREATE TABLE `orderitem` (
  `orderitemId` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `subtotal` varchar(15) NOT NULL,
  `oid` int(11) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `fprice` varchar(15) NOT NULL,
  `fimg` varchar(45) NOT NULL,
  PRIMARY KEY (`orderitemId`),
  KEY `oid_idx` (`oid`),
  CONSTRAINT `oid` FOREIGN KEY (`oid`) REFERENCES `order` (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
