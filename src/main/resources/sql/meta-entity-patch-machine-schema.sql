DROP TABLE IF EXISTS `meta_entity_patch_machine`;

CREATE TABLE `meta_entity_patch_machine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entity` varchar(64) NOT NULL COMMENT '实体名称',
  `entity_table_name` varchar(64) NOT NULL COMMENT '实体对应表名',
  `entity_field_name` varchar(64) NOT NULL COMMENT '实体字段名',
  `entity_field_type` varchar(16) NOT NULL DEFAULT 'STRING' COMMENT '实体字段类型[STRING, NUMBER, DATE]',
  `where_field_name` varchar(64) DEFAULT NULL COMMENT '实体类查询条件字段',
  `number_range_min` int(11) DEFAULT NULL COMMENT '数字类型字段的范围下限',
  `number_range_max` int(11) DEFAULT NULL COMMENT '数字类型字段的范围上限',
  `permission` varchar(64) DEFAULT NULL COMMENT '操作权限控制',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_meta_patch_entity` (`entity`,`entity_field_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

