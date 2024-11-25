package com.jfeat.module.apicode.services.gen.persistence.model.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jfeat.module.apicode.constant.RelationTypeEnum;
import lombok.Data;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 10:32
 * @author: hhhhhtao
 */
@Data
@TableName("lc_api_relation_model_tables")
public class ApiRelationModelTables {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 关系模型id
     */
    private Long relationModelId;
    /**
     * 表模型id
     */
    private Long tableModelId;
    /**
     * 建表sql
     */
    private String tableModelCreateSql;
    /**
     * 关联关系，枚举类型
     */
    private RelationTypeEnum relationType;
    /**
     * 数据关系JSON数据key，一对多默认键值为items
     */
    private String dataKey;
    /**
     * 绑定主表字段
     */
    private String relationData;
    /**
     * 绑定主表字段
     */
    private String relationMasterField;
    /**
     * 多字段关联关系通过json表达
     */
    private String multiRelationDataJson;
    /**
     * 无用字段
     */
    private Long relationMasterId;
}
