DROP TABLE IF EXISTS `lc_field_model`;
CREATE TABLE `lc_field_model` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`model_name` VARCHAR(26) DEFAULT NULL COMMENT '模板标识名称',
	`name` VARCHAR(26) DEFAULT NULL COMMENT '模板显示名称',
	`default_value` VARCHAR(26) DEFAULT NULL COMMENT '默认值',
	`is_not_null` SMALLINT(6) NOT NULL COMMENT '是否非空',
	`is_unique` SMALLINT(6) NOT NULL COMMENT '是否唯一',
	`optional_field_name` VARCHAR(26) DEFAULT NULL COMMENT '可选的字段名称',
	`data_type` VARCHAR(26) DEFAULT NULL COMMENT '字段类型',
	`field_length` SMALLINT(6) DEFAULT NULL COMMENT '字段大小',
	`field_float_length` SMALLINT(6) DEFAULT NULL COMMENT '字段小数大小',
	`comments` VARCHAR(50) DEFAULT NULL COMMENT '注释',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

