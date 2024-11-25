package com.jfeat.module.apicode.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModelTables;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiRelationModelTablesDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 14:13
 * @author: hhhhhtao
 */
public interface QueryApiRelationModelTablesDao extends BaseMapper<ApiRelationModelTables>{

    /**
     * 分页查询DTO数据列表
     *
     * @param model 关系模型表对象
     */
    Page<ApiRelationModelTablesDTO> pageDTO(Page<ApiRelationModelTablesDTO> page, @Param("model") ApiRelationModelTables model);

    /**
     * 查询DTO
     *
     * @param id 关系模型表id
     */
    ApiRelationModelTablesDTO getDTO(@Param("id") Long id);

    /**
     * 查询
     *
     * @param id 关系模型表id
     */
    ApiRelationModelTables get(@Param("id") Long id);

    /**
     * 获取列表，根据关系模型id（relationModelId）
     *
     * @param relationModelId 关系模型id
     */
    List<ApiRelationModelTables> listByRelationModelId(@Param("relationModelId") Long relationModelId);
}
