package com.jfeat.module.apicode.constant;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: lc_api_relation_model_tables表中的relation_type的枚举类
 * @project: code-stack-cloud
 * @date: 2024/4/16 10:37
 * @author: hhhhhtao
 */
@Getter
public enum RelationTypeEnum {
    /**
     * 从表
     */
    SLAVE("slave"),
    /**
     * 多对多次主表
     */
    PEER("PEER"),
    /**
     * 中间表
     */
    RELATION("RELATION"),
    /**
     * 分类表
     */
    CATEGORY("CATEGORY"),
    /**
     * 主表
     */
    MASTER("MASTER");

    /**
     * 关联关系
     */
    private final String relationType;

    RelationTypeEnum(String relationType) {
        this.relationType = relationType;
    }
}
