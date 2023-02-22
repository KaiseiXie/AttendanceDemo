/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : attendance

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2023-02-22 17:50:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint NOT NULL,
  `username` varchar(32) DEFAULT NULL,
  `clock_in_time` datetime DEFAULT NULL,
  `clock_out_time` datetime DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `times` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', 'admin', '2023-02-22 16:12:12', null, '管理员', '1677049931624');
INSERT INTO `attendance` VALUES ('1', 'admin', '2023-02-22 16:12:22', null, '管理员', '1677049942318');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `work_status` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '管理员', '1', '13812312312', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '0');
INSERT INTO `employee` VALUES ('1622849284707753985', '謝思栄', '1', '13812345678', null, null, '0');
INSERT INTO `employee` VALUES ('1622855370877825025', 'Jude', '1', '13354687954', null, null, '0');
INSERT INTO `employee` VALUES ('1622860845224685569', 'JimBean', '1', '13245687985', null, null, '0');
INSERT INTO `employee` VALUES ('1622860964628131841', 'StringerBell', '1', '13245687985', null, null, '0');
INSERT INTO `employee` VALUES ('1622861020982800386', 'Omar', '1', '13245687985', null, null, '0');
INSERT INTO `employee` VALUES ('1622861092445351938', 'Avon', '1', '13245687985', null, null, '0');
INSERT INTO `employee` VALUES ('1622861197072265217', 'TonySoporano', '1', '13245687985', null, null, '0');
INSERT INTO `employee` VALUES ('1622861642465406978', 'ArthurMorgen', '1', '13245687985', null, null, '0');
