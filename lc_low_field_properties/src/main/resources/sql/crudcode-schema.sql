SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `lc_low_field_properties`; 
CREATE TABLE `lc_low_field_properties` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`field_item_name` varchar(26) COMMENT '组件标识名称',
	`property_name` varchar(26) COMMENT '属性名称',
	`property_value` varchar(26) COMMENT '属性值',
	`scope` varchar(26) COMMENT '组件属性作用域',
	`field_id` smallint(6) NOT NULL DEFAULT 0 COMMENT '所属字段组件ID',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

