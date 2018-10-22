/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : leke

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-10-22 23:03:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_task
-- ----------------------------
DROP TABLE IF EXISTS `order_task`;
CREATE TABLE `order_task` (
  `id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mobile` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_count` bigint(20) DEFAULT NULL COMMENT '接单次数',
  `error_count` bigint(20) DEFAULT NULL COMMENT '失败次数',
  `error_msg` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '错误信息',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `session_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_task
-- ----------------------------
INSERT INTO `order_task` VALUES ('02FEB06B2494485180A456F855E47026', '13632691984', '34', '0', null, '2018-10-22 22:16:02', '2018-10-22 22:18:59', '3', null);
INSERT INTO `order_task` VALUES ('033E072009E3493B8F8E019DCABFEB27', '15818518021', '36', '0', null, '2018-10-22 22:59:18', null, '2', null);
INSERT INTO `order_task` VALUES ('179C326E3EED4B46A28DC7BFF9B9EFAD', '15018079780', '204', '0', null, '2018-10-22 22:19:38', null, '2', null);
INSERT INTO `order_task` VALUES ('21C37CFDE30748C9A63C80E77FCC6683', '15818518021', '59', '0', null, '2018-10-21 22:00:59', '2018-10-21 22:06:07', '3', null);
INSERT INTO `order_task` VALUES ('58D30CF38AA94745A16A073B485E0F6A', '15818518021', '12', '0', null, '2018-10-20 14:15:55', '2018-10-20 14:22:57', '3', null);
INSERT INTO `order_task` VALUES ('8D99E4D227144F97A3808A2B756AFC3C', '15818518021', '17', '0', null, '2018-10-22 22:01:52', '2018-10-22 22:03:22', '3', null);
INSERT INTO `order_task` VALUES ('EA0F483DD0CE4A1B8D2F12C1622DF6E3', '15818518021', '275', '0', null, '2018-10-20 23:12:11', '2018-10-20 23:38:27', '3', null);

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_pwd` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_active` int(1) DEFAULT '1',
  `real_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1', '15818518021', 'zxcvbnm123', '2018-10-16 11:19:49', '2018-11-16 11:19:52', '2018-10-16 11:19:57', '1', '1', '何小燕');
INSERT INTO `user_account` VALUES ('2', '13691645822', 'zxcvbnm123', '2018-10-16 11:19:49', '2018-11-16 11:19:52', '2018-10-16 11:19:57', '1', '1', '何小燕');
INSERT INTO `user_account` VALUES ('3', '18200898720', '871111maomao', '2018-10-16 11:19:49', '2018-11-16 11:19:52', '2018-10-16 11:19:57', '1', '1', '何金凤');
INSERT INTO `user_account` VALUES ('4', '15018079780', '120408', '2018-10-16 11:19:49', '2018-11-16 11:19:52', '2018-10-16 11:19:57', '1', '1', '何肖来');
INSERT INTO `user_account` VALUES ('5', '13360537242', 'zxcvbnm123', '2018-10-16 11:19:49', '2018-11-16 11:19:52', '2018-10-16 11:19:57', '1', '1', '何永生');
INSERT INTO `user_account` VALUES ('6', '13632691984', 'yhh840822', '2018-10-16 11:19:49', '2018-11-16 11:19:52', '2018-10-16 11:19:57', '1', '1', '杨海雄');
