SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `lc_low_filters`; 
CREATE TABLE `lc_low_filters` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`content_layout` varchar(26) COMMENT '搜索字段分布名称',
	`search_fields` varchar(26) COMMENT '引用字段组件属性',
	`default_search_hint` varchar(26) COMMENT '搜索框内提示',
	`page_id` smallint(6) NOT NULL DEFAULT 0 COMMENT '所属页面ID',
	`field_name` varchar(26) COMMENT '绑定字段名称',
	`field_type` varchar(26) COMMENT '组件类型',
	`field_title` varchar(26) COMMENT '搜索标题',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

