package com.jfeat.module.apicode.services.gen.persistence.model.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jfeat.module.apicode.constant.RelationTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.rmi.server.UID;

/**
 * @description: ApiRelationModelTablesDTO 关系模型表数据传输对象
 * @project: code-stack-cloud
 * @date: 2024/4/29 16:37
 * @author: hhhhhtao
 */
@Data
public class ApiRelationModelTablesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ApiRelationModelTables主键id
     */
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
     * 关联关系，枚举类型
     */
    private RelationTypeEnum relationType;
    /**
     * 绑定主表字段
     */
    private String relationData;
    /**
     * 表模型名称
     */
    private String tableModelName;
    /**
     * 描述
     */
    private String tableModelNotes;
}
