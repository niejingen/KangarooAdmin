/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : kangarooadmin

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-10-17 10:02:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `deptName` varchar(100) NOT NULL COMMENT '部门名称',
  `deptDesc` varchar(300) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '产品部', '1111');
INSERT INTO `sys_dept` VALUES ('69a8f0c4260f4bb7adeebdaeee4e6ca6', '风控部', '风险控制部');
INSERT INTO `sys_dept` VALUES ('98819860429e435898d7a0652ed9c5a2', '运营部', '运营部');
INSERT INTO `sys_dept` VALUES ('aebe7d9b427643feb9c5a66c65fb9a81', '行政部', 'dfdsfds');
