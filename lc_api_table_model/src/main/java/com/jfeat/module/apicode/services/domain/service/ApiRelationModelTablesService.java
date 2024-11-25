package com.jfeat.module.apicode.services.domain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModelTables;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiRelationModelTablesDTO;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 14:16
 * @author: hhhhhtao
 */
public interface ApiRelationModelTablesService {

    /**
     * 获取
     *
     * @param id api关系模型表id
     * @return api关系模型表对象
     */
    ApiRelationModelTables get(Long id);

    /**
     * 插入
     *
     * @param model api关系模型表
     * @return 影响行数
     */
    int insert(ApiRelationModelTables model);

    /**
     * 更新
     *
     * @param model api关系模型表
     * @return 影响行数量
     */
    int update(ApiRelationModelTables model);

    /**
     * 删除
     *
     * @param id api关系模型表id
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 分页查询
     * @param page mybatis-plus分页对象
     * @param model api关系模型表对象
     */
    Page<ApiRelationModelTables> page(Page<ApiRelationModelTables> page, ApiRelationModelTables model);

    /**
     * 分页查询DTO
     *
     * @param page mybatis-plus分页对象
     * @param model api关系模型表对象
     */
    Page<ApiRelationModelTablesDTO> pageDTO(Page<ApiRelationModelTablesDTO> page, ApiRelationModelTables model);

    /**
     * 获取详情
     *
     * @param id ApiRelationModelTables id
     */
    ApiRelationModelTablesDTO getDetail(Long id);
}
