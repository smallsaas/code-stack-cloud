package com.jfeat.module.apicode.services.gen.persistence.model.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jfeat.module.apicode.constant.RelationshipEnum;
import lombok.Data;

@Data
@TableName("lc_api_relation_model")
public class ApiRelationModel {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 模型标题
     */
    private String name;

    /**
     * 业务关系
     */
    private RelationshipEnum relationship;

    /**
     * 模型说明
     */
    private String notes;
}