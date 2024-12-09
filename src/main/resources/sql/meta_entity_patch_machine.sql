/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 26/06/2024 00:12:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meta_entity_patch_machine
-- ----------------------------
DROP TABLE IF EXISTS `meta_entity_patch_machine`;
CREATE TABLE `meta_entity_patch_machine`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entity` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '实体',
  `entity_table_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '实体对应表名',
  `entity_field_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '实体字段名',
  `entity_field_type` varchar(16) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'STRING' COMMENT '实体字段类型',
  `where_field_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '实体条件对应的字段名',
  `number_range_min` bigint NULL DEFAULT NULL COMMENT '数字类型字段的范围下限',
  `number_range_max` bigint NULL DEFAULT NULL COMMENT '数字类型字段的范围上限',
  `permission` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '操作权限控制',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_meta_patch_entity`(`entity` ASC, `entity_field_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meta_entity_patch_machine
-- ----------------------------
INSERT INTO `meta_entity_patch_machine` VALUES (1, 'product', 't_product', 'sort_num', 'NUMBER', NULL, 1, 10000, NULL);
INSERT INTO `meta_entity_patch_machine` VALUES (2, 'notice', 't_notice', 'sort_num', 'NUMBER', NULL, 1, 10000, NULL);
INSERT INTO `meta_entity_patch_machine` VALUES (3, 'productCategoryProperty', 't_product_category_property', 'sort_num', 'NUMBER', NULL, 1, 10000, NULL);
INSERT INTO `meta_entity_patch_machine` VALUES (4, 'productImage', 't_product_image', 'sort_num', 'NUMBER', NULL, 1, 10000, NULL);
INSERT INTO `meta_entity_patch_machine` VALUES (5, 'productTag', 't_product_tag', 'sort_num', 'NUMBER', NULL, 1, 10000, NULL);
INSERT INTO `meta_entity_patch_machine` VALUES (6, 'productCategory', 't_product_category', 't_product_category', 'NUMBER', NULL, 1, 10000, NULL);
INSERT INTO `meta_entity_patch_machine` VALUES (12, 'device', 't_device', 'status', 'STRING', NULL, NULL, NULL, NULL);
INSERT INTO `meta_entity_patch_machine` VALUES (16, 'device', 't_device', 'device_id_test', 'STRING', 'device_id_test', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
