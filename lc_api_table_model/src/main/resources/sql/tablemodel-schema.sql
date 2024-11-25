DROP TABLE IF EXISTS `lc_api_table_model`; 
CREATE TABLE `lc_api_table_model` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`model_name` varchar(50) NOT NULL COMMENT '标识名称',
	`name` varchar(50) NOT NULL COMMENT '显示名称',
	`notes` varchar(200) DEFAULT NULL COMMENT '模板说明',
UNIQUE (`model_name`),
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lc_api_table_model_field`;
CREATE TABLE `lc_api_table_model_field` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`table_model_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '主表关联ID',
	`field_model_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '字段模型关联ID',
	`default_field_name` varchar(26) DEFAULT NULL COMMENT '标准字段绑定名称',
	`field_name` varchar(26) NOT NULL COMMENT '字段名称',
	`default_value` VARCHAR(26) DEFAULT NULL COMMENT '默认值',
	`is_not_null` SMALLINT(6) NOT NULL COMMENT '是否非空',
	`is_unique` SMALLINT(6) NOT NULL COMMENT '是否唯一',
	`data_type` VARCHAR(26) DEFAULT NULL COMMENT '字段类型',
	`field_length` SMALLINT(6) DEFAULT NULL COMMENT '字段大小',
	`field_float_length` SMALLINT(6) DEFAULT NULL COMMENT '字段小数大小',
	`comments` VARCHAR(100) DEFAULT NULL COMMENT '注释',
UNIQUE (`table_model_id`,`field_name`),
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lc_api_sub_table_model`; 
CREATE TABLE `lc_api_sub_table_model` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `table_model_id` bigint(20) NOT NULL COMMENT '主表关联ID',
    `sub_table_id` bigint(20) NOT NULL COMMENT '主表作为一多对的子表',
	`sub_table_model_name` VARCHAR(50) NOT NULL COMMENT '子表标识名称（冗余主表名称）',
	`sub_table_name` VARCHAR(50) NOT NULL COMMENT '子表显示名称(冗余主表名称)',
UNIQUE (`table_model_id`,`sub_table_id`),
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
