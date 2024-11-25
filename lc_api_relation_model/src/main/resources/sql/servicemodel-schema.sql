DROP TABLE IF EXISTS `lc_api_relation_model`;
CREATE TABLE `lc_api_relation_model` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `relationship` varchar(26) NOT NULL COMMENT '[onemany,manymany,category]',
	`model_name` varchar(50) NOT NULL COMMENT '标识名称',
	`name` varchar(50) NOT NULL COMMENT '显示名称',
	`notes` varchar(200) DEFAULT NULL COMMENT '模板说明',
	UNIQUE(`model_name`),
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lc_api_relation_model_item`;
CREATE TABLE `lc_api_relation_model_item` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`relation_model_id` BIGINT(20) NOT NULL COMMENT '主表关联字段ID',
	`table_model_id` BIGINT(20) NOT NULL COMMENT '从关联表ID',
	`data_key` VARCHAR(26) DEFAULT NULL COMMENT '从表数据的一对多键值',
	`relation_type` VARCHAR(26) NOT NULL COMMENT '[master,slave,peer,relation,category]',
	`relation_data` VARCHAR(26) DEFAULT NULL COMMENT '默认绑定主表id字段',
	`multi_relation_data_json` VARCHAR(200) DEFAULT NULL COMMENT '多字段关联JSON表示',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

