/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : tim-admin

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 11/08/2023 11:07:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `sale_id` int NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `deal_num` int NULL DEFAULT NULL COMMENT '成交订单量',
  `refund_num` int NULL DEFAULT NULL COMMENT '退款订单量',
  `browse_num` int NULL DEFAULT NULL COMMENT '浏览量',
  `addpurchase_num` int NULL DEFAULT NULL COMMENT '加购量',
  `preorder_num` int NULL DEFAULT NULL COMMENT '预购量',
  PRIMARY KEY (`sale_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES (1, 1000, 2000, 3000, 4000, 5000);
INSERT INTO `sale` VALUES (2, 5000, 4000, 3000, 2000, 1000);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '123');
INSERT INTO `user` VALUES (2, 'lisi', '123');
INSERT INTO `user` VALUES (3, 'wangwu', '123');
INSERT INTO `user` VALUES (4, 'zhaoliu', '123');
INSERT INTO `user` VALUES (5, 'Tim', '123');
INSERT INTO `user` VALUES (10, 'Albus', '123');

SET FOREIGN_KEY_CHECKS = 1;
