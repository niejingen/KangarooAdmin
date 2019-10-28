/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : 127.0.0.1:3306
Source Database       : kangarooadmin

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-10-17 10:02:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `userState` int(2) NOT NULL DEFAULT '1' COMMENT '用户状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userDesc` varchar(300) DEFAULT NULL COMMENT '描述',
  `userImg` varchar(300) DEFAULT 'http://news.mydrivers.com/Img/20110518/04481549.png' COMMENT '头像',
  `deptId` varchar(50) DEFAULT NULL COMMENT '部门主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('09c63f873a9e472ca464accb61cd5e51', 'test', 'DC483E80A7A0BD9EF71D8CF973673924', '-1', '2016-12-12 15:50:39', 'aaaaa', 'http://news.mydrivers.com/Img/20110518/04481549.png', '98819860429e435898d7a0652ed9c5a2');
INSERT INTO `sys_user` VALUES ('4754f010ef344c59b728ea60809ab926', 'e100000', '1973EBD114AAB8BD85457E037BBBFA62', '1', '2016-12-12 13:43:59', 'aa44515112121', 'http://news.mydrivers.com/Img/20110518/04481549.png', 'aebe7d9b427643feb9c5a66c65fb9a81');
INSERT INTO `sys_user` VALUES ('549d321508db446e9bcaa477835fe5f1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-12-14 14:35:08', '所有权限', 'http://yosamall.bj.bcebos.com/temp/h_main_NNN4_e80a000007df111a.jpg', '1');
INSERT INTO `sys_user` VALUES ('629ba7eb1d8944d2873ecfc6896288e7', 'zhangsan', '25F9E794323B453885F5181F1B624D0B', '1', '2016-12-12 11:49:21', '张三负责系统的委会和开发工作。', 'http://news.mydrivers.com/Img/20110518/04481549.png', '1');
