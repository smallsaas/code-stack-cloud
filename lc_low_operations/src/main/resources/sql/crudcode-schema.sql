SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `lc_low_operations`; 
CREATE TABLE `lc_low_operations` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`title` VARCHAR(50) DEFAULT NULL COMMENT '标题',
	`type` VARCHAR(26) DEFAULT NULL COMMENT '操作类型',
	`path` VARCHAR(255) DEFAULT NULL COMMENT '操作路由路径',
	`outside` SMALLINT(6) DEFAULT NULL COMMENT '是否显示在列表中',
	`request_api` VARCHAR(200) DEFAULT NULL COMMENT '请求API',
	`request_refresh_api` VARCHAR(200) DEFAULT NULL COMMENT '请求结果API',
	`request_method` VARCHAR(26) DEFAULT NULL COMMENT '请求方法',
	`request_body` TEXT DEFAULT NULL COMMENT '请求数据',
	`request_options` VARCHAR(26) DEFAULT NULL COMMENT '请求参数',
	`page_id` BIGINT(20) NOT NULL COMMENT '所属页面ID',
	`expect_field` VARCHAR(26) DEFAULT NULL COMMENT '过滤字段',
	`expect_value` VARCHAR(26) DEFAULT NULL COMMENT '过滤值',
	`modal_title` VARCHAR(26) DEFAULT NULL COMMENT '模态框标题',
	`modal_width` SMALLINT(6) DEFAULT NULL COMMENT '模态框宽度',
	`modal_layout` VARCHAR(26) DEFAULT NULL COMMENT '模态框外部布局',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

