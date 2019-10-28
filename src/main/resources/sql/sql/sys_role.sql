/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : kangarooadmin

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-10-17 10:02:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  `roleDesc` varchar(300) DEFAULT NULL COMMENT '角色描述',
  `roleState` int(2) DEFAULT '1' COMMENT '状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('737933bffef640329a4f864c4e2746ba', '超级管理员', 'utyutyuytuytuytuhghg', '1', '2016-12-14 10:22:34');
INSERT INTO `sys_role` VALUES ('a21876314a764438b6af6bfa422ec09a', '系统管理员', '111118455', '1', '2016-12-14 17:53:25');
INSERT INTO `sys_role` VALUES ('ab7e4b34e5d141fa8566fdbb5d3e66f7', '报表管理员', 'dasdasdas', '1', '2016-12-15 20:00:21');
INSERT INTO `sys_role` VALUES ('eac1b2f4ff1d49af891872a847f9dd79', 'khj', 'khkhj', '1', '2016-12-21 15:18:11');
INSERT INTO `sys_role` VALUES ('fdce142ce7554e30b3274c6d8844b13e', '商品管理员', 'fdfdfdf', '1', '2016-12-15 19:59:59');
