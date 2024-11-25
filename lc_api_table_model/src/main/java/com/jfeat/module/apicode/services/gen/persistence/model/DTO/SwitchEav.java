package com.jfeat.module.apicode.services.gen.persistence.model.DTO;

import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import lombok.Data;

import java.util.List;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/5/17 09:51
 * @author: hhhhhtao
 */
@Data
public class SwitchEav {
    /**
     * 实体类型
     */
    private String entityType;
    /**
     * 绑定的实体名（比较绕，这个字段指定的实体名是生成代码的时候所生成的一个实体的名称，然后将这个eav数据绑定在指定的实体上
     * 例如：生成代码时我用的表名字为`t_project`那么生成的实体名就会是 Project，然后该entityName的值是Project的话就可以将该eav数据绑定在那个实体上，查询的时候可以一并查出该字段
     */
    private String entityName;
    /**
     * eav名
     */
    private String name;
    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 字段列表
     */
    private List<ApiTableModelField> fields;
}
