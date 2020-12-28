/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : onlineschool

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-12-27 16:30:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ainfo
-- ----------------------------
DROP TABLE IF EXISTS `ainfo`;
CREATE TABLE `ainfo` (
  `aNo` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `aPassword` varchar(255) DEFAULT NULL,
  `aGrade` varchar(255) DEFAULT NULL,
  `aPhoneNum` varchar(255) DEFAULT NULL,
  `aSex` varchar(255) DEFAULT NULL,
  `aEmail` varchar(255) DEFAULT NULL,
  `aAge` int(11) DEFAULT NULL,
  PRIMARY KEY (`aNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ainfo
-- ----------------------------
INSERT INTO `ainfo` VALUES ('007', '1234', '管理员', '13377777777', '女', '999@qq.com', '34');
INSERT INTO `ainfo` VALUES ('008', '81dc9bdb52d04dc20036dbd8313ed055', '管理员', '13377777777', '女', '777@qq.com', '34');

-- ----------------------------
-- Table structure for cinfo
-- ----------------------------
DROP TABLE IF EXISTS `cinfo`;
CREATE TABLE `cinfo` (
  `cNo` varchar(255) NOT NULL,
  `cName` varchar(255) DEFAULT NULL,
  `cType` varchar(255) DEFAULT NULL,
  `cIntro` varchar(255) DEFAULT NULL,
  `cVideo` varchar(255) DEFAULT NULL,
  `cpic` varchar(255) DEFAULT NULL,
  `cMoney` int(11) DEFAULT NULL,
  `cStatus` int(11) DEFAULT NULL,
  `release_id` int(11) DEFAULT NULL,
  `cViewInfo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cinfo
-- ----------------------------
INSERT INTO `cinfo` VALUES ('0001', 'DNA双螺旋', '基础医学', 'DNA双螺旋', 'http://localhost:8090/onlineschool/video/001.mp4', 'http://localhost:8090/onlineschool/pic/001.jpg', '10', '1', '1', '1');
INSERT INTO `cinfo` VALUES ('0002', '病毒繁殖', '医学影像学', '病毒繁殖', 'http://localhost:8090/onlineschool/video/002.mp4', 'http://localhost:8090/onlineschool/pic/002.jpg', '60', '2', '2', '2');
INSERT INTO `cinfo` VALUES ('0003', '神经系统', '临床医学', '神经系统', 'http://localhost:8090/onlineschool/video/003.mp4', 'http://localhost:8090/onlineschool/pic/003.jpg', '30', '1', '3', '3');
INSERT INTO `cinfo` VALUES ('0004', '护齿', '口腔医学', '护齿', 'http://localhost:8090/onlineschool/video/004.mp4', 'http://localhost:8090/onlineschool/pic/004.png', '10', '1', '4', '4');
INSERT INTO `cinfo` VALUES ('0005', '脑部结构', '基础医学', '脑部结构', 'http://localhost:8090/onlineschool/video/005.mp4', 'http://localhost:8090/onlineschool/pic/005.png', '60', '0', '5', '5');
INSERT INTO `cinfo` VALUES ('0006', '运动康复', '运动康复学', '运动康复', 'http://localhost:8090/onlineschool/video/006.mp4', 'http://localhost:8090/onlineschool/pic/006.png', '30', '1', '6', '6');
INSERT INTO `cinfo` VALUES ('0007', '中草药', '药学', '中草药', 'http://localhost:8090/onlineschool/video/007.mp4', 'http://localhost:8090/onlineschool/pic/007.png', '20', '1', '7', '7');
INSERT INTO `cinfo` VALUES ('0008', '医学研究室', '基础医学', '基础医学', 'http://localhost:8090/onlineschool/video/008.mp4', '\"http://localhost:8090/onlineschool/pic/002.jpg\"', '20', '1', '8', '8');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `ParentId` int(11) DEFAULT NULL,
  `commtime` date DEFAULT NULL,
  `comms` varchar(255) DEFAULT NULL,
  `cInfo_id` varchar(255) DEFAULT NULL,
  `uInfo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', null, '2020-12-21', '很棒', '0001', '1');
INSERT INTO `comment` VALUES ('2', '1', '2020-12-22', '哈哈', '0001', '2');
INSERT INTO `comment` VALUES ('3', null, '2020-12-24', '。。。', '0002', '1');
INSERT INTO `comment` VALUES ('4', null, '2020-12-24', '学废了', '0002', '3');
INSERT INTO `comment` VALUES ('5', null, '2020-12-26', '第一', '0003', '1');
INSERT INTO `comment` VALUES ('6', '5', '2020-12-27', '顶一个', '0003', '2');
INSERT INTO `comment` VALUES ('7', '5', '2020-12-27', '666', '0003', '3');
INSERT INTO `comment` VALUES ('8', null, '2020-12-12', '海量资源', '0004', '2');
INSERT INTO `comment` VALUES ('9', '8', '2020-12-15', '加我微信', '0004', '1');
INSERT INTO `comment` VALUES ('10', null, '2020-12-25', '沙发', '0005', '1');
INSERT INTO `comment` VALUES ('11', null, '2020-12-27', '活到老，学到老', '0006', '3');
INSERT INTO `comment` VALUES ('12', null, '2020-12-28', '支持up主，加油！！！', '0003', '2');
INSERT INTO `comment` VALUES ('13', null, '2020-12-27', '白毛浮绿水', '0006', '1');
INSERT INTO `comment` VALUES ('14', '13', '2020-12-27', '低头思故乡', '0006', '2');

-- ----------------------------
-- Table structure for cviewinfo
-- ----------------------------
DROP TABLE IF EXISTS `cviewinfo`;
CREATE TABLE `cviewinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clike` int(11) DEFAULT NULL,
  `cComment` int(11) DEFAULT NULL,
  `cPageView` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkeycNocView` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cviewinfo
-- ----------------------------
INSERT INTO `cviewinfo` VALUES ('1', '99', '2', '100');
INSERT INTO `cviewinfo` VALUES ('2', '33', '2', '55');
INSERT INTO `cviewinfo` VALUES ('3', '44', '4', '99');
INSERT INTO `cviewinfo` VALUES ('4', '22', '2', '77');
INSERT INTO `cviewinfo` VALUES ('5', '11', '1', '88');
INSERT INTO `cviewinfo` VALUES ('6', '100', '3', '88');
INSERT INTO `cviewinfo` VALUES ('7', '87', '0', '99');

-- ----------------------------
-- Table structure for like1
-- ----------------------------
DROP TABLE IF EXISTS `like1`;
CREATE TABLE `like1` (
  `likeId` int(11) NOT NULL AUTO_INCREMENT,
  `cInfo_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `uInfo_id` int(11) DEFAULT NULL,
  `liketime` date DEFAULT NULL,
  PRIMARY KEY (`likeId`),
  KEY `fkeycNolike` (`cInfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of like1
-- ----------------------------

-- ----------------------------
-- Table structure for release1
-- ----------------------------
DROP TABLE IF EXISTS `release1`;
CREATE TABLE `release1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uInfo_id` int(11) DEFAULT NULL,
  `rTime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkeyuIdrel` (`uInfo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of release1
-- ----------------------------
INSERT INTO `release1` VALUES ('1', '1', '2020-12-21');
INSERT INTO `release1` VALUES ('2', '2', '2020-12-23');
INSERT INTO `release1` VALUES ('3', '3', '2020-12-25');
INSERT INTO `release1` VALUES ('4', '1', '2020-12-10');
INSERT INTO `release1` VALUES ('5', '3', '2020-12-24');
INSERT INTO `release1` VALUES ('6', '1', '2020-12-25');
INSERT INTO `release1` VALUES ('7', '1', '2020-12-12');

-- ----------------------------
-- Table structure for subscribe
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe` (
  `sId` int(11) NOT NULL AUTO_INCREMENT,
  `cInfo_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sTime` date DEFAULT NULL,
  `uInfo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sId`),
  KEY `fkeyuIdsub` (`cInfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subscribe
-- ----------------------------

-- ----------------------------
-- Table structure for swiper
-- ----------------------------
DROP TABLE IF EXISTS `swiper`;
CREATE TABLE `swiper` (
  `sId` int(11) NOT NULL AUTO_INCREMENT,
  `sPic` varchar(255) DEFAULT NULL,
  `sChain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`sId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of swiper
-- ----------------------------
INSERT INTO `swiper` VALUES ('1', 'http://localhost:8090/onlineschool/swiper/01.jpg', null);
INSERT INTO `swiper` VALUES ('2', 'http://localhost:8090/onlineschool/swiper/02.jpg', null);
INSERT INTO `swiper` VALUES ('3', 'http://localhost:8090/onlineschool/swiper/03.jpg', null);
INSERT INTO `swiper` VALUES ('4', 'http://localhost:8090/onlineschool/swiper/04.jpg', null);
INSERT INTO `swiper` VALUES ('5', 'http://localhost:8090/onlineschool/swiper/05.jpg', null);
INSERT INTO `swiper` VALUES ('6', 'http://localhost:8090/onlineschool/swiper/06.jpg', null);
INSERT INTO `swiper` VALUES ('7', 'http://localhost:8090/onlineschool/swiper/9931171b-edeb-4e1b-904f-4cebad3d7cf7.jpg', '');
INSERT INTO `swiper` VALUES ('8', 'http://localhost:8090/onlineschool/swiper/3636a3f5-9db2-44ef-9728-ed4c9f6d0be5.jpg', '');

-- ----------------------------
-- Table structure for uinfo
-- ----------------------------
DROP TABLE IF EXISTS `uinfo`;
CREATE TABLE `uinfo` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(255) DEFAULT NULL,
  `uPassword` varchar(255) DEFAULT NULL,
  `uSex` varchar(255) DEFAULT NULL,
  `uAge` int(11) DEFAULT NULL,
  `uEmail` varchar(255) DEFAULT NULL,
  `uPic` varchar(255) DEFAULT NULL,
  `uMoneyInfo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uinfo
-- ----------------------------
INSERT INTO `uinfo` VALUES ('1', '伽罗', 'e10adc3949ba59abbe56e057f20f883e', '女', '18', '123@qq.com', 'http://localhost:8090/onlineschool/headpic/jialuo.jpg', '1');
INSERT INTO `uinfo` VALUES ('2', '后裔', 'e10adc3949ba59abbe56e057f20f883e', '男', '19', '456@163.com', 'http://localhost:8090/onlineschool/headpic/houyi.jpg', '2');
INSERT INTO `uinfo` VALUES ('3', '东皇太一', 'e10adc3949ba59abbe56e057f20f883e', '男', '30', '789@qq.com', 'http://localhost:8090/onlineschool/headpic/donghuang.jpg', '3');

-- ----------------------------
-- Table structure for umoneyinfo
-- ----------------------------
DROP TABLE IF EXISTS `umoneyinfo`;
CREATE TABLE `umoneyinfo` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uMoney` int(11) NOT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of umoneyinfo
-- ----------------------------
INSERT INTO `umoneyinfo` VALUES ('1', '20');
INSERT INTO `umoneyinfo` VALUES ('2', '30');
INSERT INTO `umoneyinfo` VALUES ('3', '40');
INSERT INTO `umoneyinfo` VALUES ('8', '0');

-- ----------------------------
-- Table structure for uorder
-- ----------------------------
DROP TABLE IF EXISTS `uorder`;
CREATE TABLE `uorder` (
  `uInfo_id` int(11) DEFAULT NULL,
  `oId` varchar(255) NOT NULL,
  `oMoney` int(11) DEFAULT NULL,
  `oTime` date DEFAULT NULL,
  `oType` int(11) DEFAULT NULL,
  PRIMARY KEY (`oId`),
  KEY `fkeyuIdor` (`uInfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uorder
-- ----------------------------
