package com.jfeat.module.apicode.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.module.apicode.services.domain.dao.QueryApiRelationModelTablesDao;
import com.jfeat.module.apicode.services.domain.service.ApiRelationModelTablesService;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModelTables;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiRelationModelTablesDTO;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiTableModelDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 14:18
 * @author: hhhhhtao
 */
@Slf4j
@Service("ApiRelationModelTablesService")
public class ApiRelationModelTablesServiceImpl implements ApiRelationModelTablesService {

    @Resource
    QueryApiRelationModelTablesDao queryApiRelationModelTablesDao;

    /**
     * 获取
     *
     * @param id api关系模型表id
     * @return api关系模型表对象
     */
    @Override
    public ApiRelationModelTables get(Long id) {
        return queryApiRelationModelTablesDao.selectById(id);
    }

    /**
     * 插入
     *
     * @param model api关系模型表
     * @return 影响行数
     */
    @Override
    public int insert(ApiRelationModelTables model) {
        return queryApiRelationModelTablesDao.insert(model);
    }

    /**
     * 更新
     *
     * @param model api关系模型表
     * @return 影响行数量
     */
    @Override
    public int update(ApiRelationModelTables model) {
        return queryApiRelationModelTablesDao.updateById(model);
    }

    /**
     * 删除
     *
     * @param id api关系模型表id
     * @return 影响行数
     */
    @Override
    public int delete(Long id) {
        return queryApiRelationModelTablesDao.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param page  mybatis-plus分页对象
     * @param model api关系模型表对象
     * @return Page对象
     */
    @Override
    public Page<ApiRelationModelTables> page(Page<ApiRelationModelTables> page, ApiRelationModelTables model) {
        // 封装查询条件
        LambdaQueryWrapper<ApiRelationModelTables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(model.getId() != null, ApiRelationModelTables::getId, model.getId());
        queryWrapper.eq(model.getRelationModelId() != null, ApiRelationModelTables::getRelationModelId, model.getRelationModelId());
        queryWrapper.eq(model.getTableModelId() != null, ApiRelationModelTables::getTableModelId, model.getTableModelId());
        queryWrapper.eq(StringUtils.isNotBlank(model.getTableModelCreateSql()), ApiRelationModelTables::getTableModelCreateSql, model.getTableModelCreateSql());
        queryWrapper.eq(model.getRelationType() != null, ApiRelationModelTables::getRelationType, model.getRelationType());
        queryWrapper.eq(StringUtils.isNotBlank(model.getDataKey()), ApiRelationModelTables::getDataKey, model.getDataKey());
        queryWrapper.eq(StringUtils.isNotBlank(model.getRelationData()), ApiRelationModelTables::getRelationData, model.getRelationData());
        queryWrapper.eq(StringUtils.isNotBlank(model.getRelationMasterField()), ApiRelationModelTables::getRelationMasterField, model.getRelationMasterField());
        queryWrapper.eq(StringUtils.isNotBlank(model.getMultiRelationDataJson()), ApiRelationModelTables::getMultiRelationDataJson, model.getMultiRelationDataJson());
        queryWrapper.eq(model.getRelationMasterId() != null, ApiRelationModelTables::getRelationMasterId, model.getRelationMasterId());
        // 执行查询
        return queryApiRelationModelTablesDao.selectPage(page, queryWrapper);
    }

    /**
     * 分页查询DTO
     *
     * @param page  mybatis-plus分页对象
     * @param model api关系模型表对象
     */
    @Override
    public Page<ApiRelationModelTablesDTO> pageDTO(Page<ApiRelationModelTablesDTO> page, ApiRelationModelTables model) {
        return queryApiRelationModelTablesDao.pageDTO(page, model);
    }

    /**
     * 获取详情
     *
     * @param id ApiRelationModelTables id
     */
    @Override
    public ApiRelationModelTablesDTO getDetail(Long id) {
        return queryApiRelationModelTablesDao.getDTO(id);
    }
}
