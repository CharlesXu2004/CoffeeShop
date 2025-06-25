/*
 Navicat MySQL Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80042 (8.0.42)
 Source Host           : localhost:3306
 Source Schema         : dev

 Target Server Type    : MySQL
 Target Server Version : 80042 (8.0.42)
 File Encoding         : 65001

 Date: 25/06/2025 09:05:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cost` decimal(10, 2) NULL DEFAULT NULL,
  `goods_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`id` DESC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (30, 'Coffee Beverage 和 Ice 3份', 11.40, '[{\"name\":\"coffee\",\"num\":1,\"price\":9.9},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (29, 'Coca Beverage 和 Ice 3份 和 Milk 3份', 7.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5},{\"name\":\"milk\",\"num\":3,\"price\":1}]');
INSERT INTO `order` VALUES (28, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (27, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (26, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (25, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (24, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (23, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (22, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (21, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (20, 'Coca Beverage 和 Ice 1份', 3.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":1,\"price\":0.5}]');
INSERT INTO `order` VALUES (19, 'Coca Beverage', 3.00, '[{\"name\":\"coca\",\"num\":1,\"price\":3}]');
INSERT INTO `order` VALUES (18, 'Coca Beverage', 3.00, '[{\"name\":\"coca\",\"num\":1,\"price\":3}]');
INSERT INTO `order` VALUES (17, 'Coca Beverage 和 Ice 1份', 3.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":1,\"price\":0.5}]');
INSERT INTO `order` VALUES (16, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (15, 'Coca Beverage 和 Ice 2份', 4.00, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":2,\"price\":0.5}]');
INSERT INTO `order` VALUES (14, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (13, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (12, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (11, 'Coca Beverage 和 Ice 2份', 4.00, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":2,\"price\":0.5}]');
INSERT INTO `order` VALUES (10, 'Coca Beverage 和 Ice 3份', 4.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5}]');
INSERT INTO `order` VALUES (9, 'Coca Beverage 和 Ice 2份', 4.00, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":2,\"price\":0.5}]');
INSERT INTO `order` VALUES (8, 'Coca Beverage 和 Ice 1份 和 Milk 2份', 5.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":1,\"price\":0.5},{\"name\":\"milk\",\"num\":2,\"price\":1}]');
INSERT INTO `order` VALUES (7, 'Coca Beverage 和 Ice 1份 和 Milk 2份', 5.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":1,\"price\":0.5},{\"name\":\"milk\",\"num\":2,\"price\":1}]');
INSERT INTO `order` VALUES (6, 'Coca Beverage 和 Ice 1份', 3.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":1,\"price\":0.5}]');
INSERT INTO `order` VALUES (5, 'Coca Beverage 和 Ice 3份 和 Milk 4份', 8.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5},{\"name\":\"milk\",\"num\":4,\"price\":1}]');
INSERT INTO `order` VALUES (4, 'Coca Beverage 和 Ice 3份 和 Milk 4份', 8.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5},{\"name\":\"milk\",\"num\":4,\"price\":1}]');
INSERT INTO `order` VALUES (3, 'Coca Beverage 和 Ice 3份 和 Milk 4份', 8.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5},{\"name\":\"milk\",\"num\":4,\"price\":1}]');
INSERT INTO `order` VALUES (2, 'Coca Beverage 和 Ice 3份 和 Milk 4份', 8.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5},{\"name\":\"milk\",\"num\":4,\"price\":1}]');
INSERT INTO `order` VALUES (1, 'Coca Beverage 和 Ice 3份 和 Milk 4份', 8.50, '[{\"name\":\"coca\",\"num\":1,\"price\":3},{\"name\":\"ice\",\"num\":3,\"price\":0.5},{\"name\":\"milk\",\"num\":4,\"price\":1}]');

SET FOREIGN_KEY_CHECKS = 1;
