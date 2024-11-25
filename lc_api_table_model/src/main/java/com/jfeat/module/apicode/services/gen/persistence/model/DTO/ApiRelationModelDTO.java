package com.jfeat.module.apicode.services.gen.persistence.model.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jfeat.module.apicode.constant.RelationshipEnum;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModelTables;
import lombok.Data;

import java.util.List;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/5/6 11:08
 * @author: hhhhhtao
 */
@Data
public class ApiRelationModelDTO {

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

    /**
     * 关联的数据模型关系
     */
    private List<ApiRelationModelTables> relationTables;
}
