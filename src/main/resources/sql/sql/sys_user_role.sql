/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : kangarooadmin

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-10-17 10:03:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `Id` varchar(50) NOT NULL COMMENT '主键',
  `userId` varchar(50) NOT NULL COMMENT '用户主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1025415191074c3f8e515ea7b4720ac1', '6e6594f930054f1297f93ea626db9500', 'on');
INSERT INTO `sys_user_role` VALUES ('210e680232964f94acc73b402483192d', '9df9f873d44a460fae8b2d4ffc726808', '1');
INSERT INTO `sys_user_role` VALUES ('35d6cc2492e9431b9766e7b5738719f8', '549d321508db446e9bcaa477835fe5f1', '737933bffef640329a4f864c4e2746ba');
INSERT INTO `sys_user_role` VALUES ('3afdaa0330fa471694216234da1ed94d', 'f62307be393d4f5a9a61ed9116629b03', 'a21876314a764438b6af6bfa422ec09a');
INSERT INTO `sys_user_role` VALUES ('3e3841367e644fbb8bbc44deaa179516', '3b3fc94b2c064cd5839d0184e6be4857', '2');
INSERT INTO `sys_user_role` VALUES ('8b990b3b7fe74ce0b9bf81b966a67b9d', '3b3fc94b2c064cd5839d0184e6be4857', '1');
INSERT INTO `sys_user_role` VALUES ('9cbd08ed61624d40bb2e8ef4885e9e74', 'b55a9c253c83412aaf15aeb044899230', '3');
INSERT INTO `sys_user_role` VALUES ('d2cc10c108164ab582c3a201492b5092', '9df9f873d44a460fae8b2d4ffc726808', '2');
INSERT INTO `sys_user_role` VALUES ('f4a6696fde4f4406a6a9749d46b37e53', '9df9f873d44a460fae8b2d4ffc726808', '3');
INSERT INTO `sys_user_role` VALUES ('f4c549066f9c442782a166da6aa65654', '6e6594f930054f1297f93ea626db9500', 'on');
