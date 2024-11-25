SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `lc_low_main_page`;
CREATE TABLE `lc_low_main_page` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `page_id` bigint DEFAULT NULL,
	`page_title` varchar(50) COMMENT '页面标题',
	`page_name` varchar(50) COMMENT '页面名称',
	`column_align` varchar(26) COMMENT '行对齐',
	`api_endpoint` varchar(50) COMMENT '数据接口',
	`form_add_title` varchar(50) COMMENT '新建页面标题',
	`form_view_title` varchar(50) COMMENT '页面视图标题',
	`form_edit_title` varchar(50) COMMENT '页面编辑标题',
	`content_layout` varchar(26) COMMENT '页面内容布局',
	`content_items` varchar(26) COMMENT '组件列表排序',
	`content_item_container_style` varchar(1024) COMMENT '组件容器属性',
	`list_fields` varchar(1024) COMMENT '列表字段',
	`list_operation_fields` varchar(1024) COMMENT '列表操作排列',
	`form_add_fields` varchar(1024) COMMENT '表单新建字段排列',
	`form_view_fields` varchar(1024) COMMENT '列表视图字段排列',
	`form_edit_fields` varchar(1024) COMMENT '列表编辑字段排列',
	`form_default_content_layout` varchar(26) COMMENT '表单默认内容布局',
	`form_default_width` smallint(6) COMMENT '表单模态框默认宽度',
	`page_min_width` smallint(6) COMMENT '页面最小宽度',
	`search_type` varchar(26) COMMENT '搜索类型',
	`search_button_type` varchar(26) COMMENT '搜索按钮类型',
    `front_url` varchar(255) DEFAULT NULL COMMENT '前端路由地址',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
