/*
Navicat MySQL Data Transfer

Source Server         : payroll
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : sxt tutorial

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2021-07-19 10:43:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `auditing`
-- ----------------------------
DROP TABLE IF EXISTS `auditing`;
CREATE TABLE `auditing` (
  `auditid` int(10) NOT NULL AUTO_INCREMENT,
  `expid` int(10) DEFAULT NULL,
  `empid` varchar(10) DEFAULT NULL,
  `result` varchar(10) DEFAULT NULL,
  `auditdesc` varchar(50) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`auditid`),
  KEY `FK_AUDITING_REFERENCE_EMPLOYEE` (`empid`),
  KEY `FK_AUDITING_REFERENCE_EXPENSE` (`expid`),
  CONSTRAINT `FK_AUDITING_REFERENCE_EMPLOYEE` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`),
  CONSTRAINT `FK_AUDITING_REFERENCE_EXPENSE` FOREIGN KEY (`expid`) REFERENCES `expense` (`expid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auditing
-- ----------------------------

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptno` int(3) NOT NULL,
  `deptname` varchar(15) DEFAULT NULL,
  `location` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', 'Khoury College', 'SV');

-- ----------------------------
-- Table structure for `duty`
-- ----------------------------
DROP TABLE IF EXISTS `duty`;
CREATE TABLE `duty` (
  `dtid` int(10) NOT NULL AUTO_INCREMENT,
  `emprid` varchar(10) DEFAULT NULL,
  `dtdate` date DEFAULT NULL,
  `signintime` time DEFAULT NULL,
  `signouttime` time DEFAULT NULL,
  PRIMARY KEY (`dtid`),
  KEY `FK_DUTY_REFERENCE_EMPLOYEE` (`emprid`),
  CONSTRAINT `FK_DUTY_REFERENCE_EMPLOYEE` FOREIGN KEY (`emprid`) REFERENCES `employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of duty
-- ----------------------------

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `empid` varchar(10) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  `deptno` int(3) DEFAULT NULL,
  `posid` int(5) DEFAULT NULL,
  `mgrid` varchar(10) DEFAULT NULL,
  `realname` varchar(12) DEFAULT NULL,
  `sex` char(3) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `leavedate` date DEFAULT NULL,
  `onduty` int(1) DEFAULT NULL COMMENT '0-��ְ  1-��ְ',
  `emptype` int(1) DEFAULT NULL COMMENT '1.��ͨԱ��  2.������Ա ��������ܼࡢ�ܲõ�  3.����Ա',
  `phone` char(11) DEFAULT NULL,
  `email` varchar(10) DEFAULT NULL,
  `emercontactperson` varchar(200) DEFAULT NULL,
  `idcard` char(18) DEFAULT NULL,
  PRIMARY KEY (`empid`),
  KEY `FK_EMPLOYEE_REFERENCE_DEPT` (`deptno`),
  KEY `FK_EMPLOYEE_REFERENCE_POSITION` (`posid`),
  KEY `FK_EMPLOYEE_REFERENCE_EMPLOYEE` (`mgrid`),
  CONSTRAINT `FK_EMPLOYEE_REFERENCE_DEPT` FOREIGN KEY (`deptno`) REFERENCES `dept` (`deptno`),
  CONSTRAINT `FK_EMPLOYEE_REFERENCE_EMPLOYEE` FOREIGN KEY (`mgrid`) REFERENCES `employee` (`empid`),
  CONSTRAINT `FK_EMPLOYEE_REFERENCE_POSITION` FOREIGN KEY (`posid`) REFERENCES `position` (`posid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('ryanmah', '123', null, null, null, 'Ryan Mah', null, null, null, null, null, '1', null, null, null, null);
INSERT INTO `employee` VALUES ('ingridliu', '123', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `expense`
-- ----------------------------
DROP TABLE IF EXISTS `expense`;
CREATE TABLE `expense` (
  `expid` int(10) NOT NULL AUTO_INCREMENT,
  `empid` varchar(10) DEFAULT NULL,
  `totalamount` double(10,2) DEFAULT NULL,
  `exptime` datetime DEFAULT NULL,
  `expdesc` varchar(100) DEFAULT NULL,
  `nextauditor` varchar(10) DEFAULT NULL,
  `lastResult` varchar(20) DEFAULT NULL,
  `status` char(1) DEFAULT NULL COMMENT '1�������   2 ��˽���ͨ��  3 ��˽�������',
  PRIMARY KEY (`expid`),
  KEY `FK_EXPENSE_REFERENCE_EMPLOYEE` (`empid`),
  CONSTRAINT `FK_EXPENSE_REFERENCE_EMPLOYEE` FOREIGN KEY (`empid`) REFERENCES `employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expense
-- ----------------------------

-- ----------------------------
-- Table structure for `expenseitem`
-- ----------------------------
DROP TABLE IF EXISTS `expenseitem`;
CREATE TABLE `expenseitem` (
  `itemid` int(10) NOT NULL AUTO_INCREMENT,
  `expid` int(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `amount` double(7,2) DEFAULT NULL,
  `itemdesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `FK_EXPENSEI_REFERENCE_EXPENSE` (`expid`),
  CONSTRAINT `FK_EXPENSEI_REFERENCE_EXPENSE` FOREIGN KEY (`expid`) REFERENCES `expense` (`expid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expenseitem
-- ----------------------------

-- ----------------------------
-- Table structure for `expimage`
-- ----------------------------
DROP TABLE IF EXISTS `expimage`;
CREATE TABLE `expimage` (
  `imgid` int(10) NOT NULL AUTO_INCREMENT,
  `expid` int(10) DEFAULT NULL,
  `realname` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `filetype` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`imgid`),
  KEY `FK_EXPIMAGE_REFERENCE_EXPENSE` (`expid`),
  CONSTRAINT `FK_EXPIMAGE_REFERENCE_EXPENSE` FOREIGN KEY (`expid`) REFERENCES `expense` (`expid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expimage
-- ----------------------------

-- ----------------------------
-- Table structure for `income`
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `icid` int(10) NOT NULL AUTO_INCREMENT,
  `amount` int(10) DEFAULT NULL,
  `icdate` datetime DEFAULT NULL,
  `ictype` varchar(10) DEFAULT NULL,
  `indesc` varchar(100) DEFAULT NULL,
  `userid` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`icid`),
  KEY `FK_INCOME_REFERENCE_EMPLOYEE` (`userid`),
  CONSTRAINT `FK_INCOME_REFERENCE_EMPLOYEE` FOREIGN KEY (`userid`) REFERENCES `employee` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income
-- ----------------------------

-- ----------------------------
-- Table structure for `payment`
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `payempid` varchar(10) DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `paytime` datetime DEFAULT NULL,
  `expid` int(10) DEFAULT NULL,
  `empid` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `FK_PAYMENT_REFERENCE_EXPENSE` (`expid`),
  CONSTRAINT `FK_PAYMENT_REFERENCE_EXPENSE` FOREIGN KEY (`expid`) REFERENCES `expense` (`expid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------

-- ----------------------------
-- Table structure for `position`
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `posid` int(5) NOT NULL,
  `pname` varchar(15) DEFAULT NULL,
  `pdesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`posid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', 'Campus Director', null);
INSERT INTO `position` VALUES ('2', 'Teacher Manager', null);
INSERT INTO `position` VALUES ('3', 'Professor', null);
INSERT INTO `position` VALUES ('4', 'Lecturer', null);
INSERT INTO `position` VALUES ('5', 'Advisor', null);
