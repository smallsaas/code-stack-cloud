DROP TABLE IF EXISTS `meta_status_machine`;

CREATE TABLE `meta_status_machine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `entity` varchar(64) NOT NULL COMMENT '实体',
  `entity_table_name` varchar(64) NOT NULL COMMENT '实体对应的表名',
  `from_status` varchar(64) NOT NULL COMMENT '原状态',
  `to_status` varchar(64) NOT NULL COMMENT '下一个状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8