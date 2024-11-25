package com.jfeat.module.apicode.constant;

import lombok.Getter;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/17 11:24
 * @author: hhhhhtao
 */
@Getter
public enum RelationshipEnum {

    /**
     * 单表
     */
    ONE("ONE"),
    /**
     * 一对多
     */
    ONEMANY("ONEMANY"),
    /**
     * 多对多
     */
    MANYMANY("MANYMANY"),
    /**
     * 实体层次分类
     */
    CATEGORY("CATEGORY");

    /**
     * 业务关系
     */
    private final String relationship;

    RelationshipEnum(String relationship) {
        this.relationship = relationship;
    }
}
