package com.jfeat.module.apicode.services.domain.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.CgParam;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 14:17
 * @author: hhhhhtao
 */
public interface ApiRelationModelService {

    /**
     * 新增
     *
     * @param apiRelationModel api关系模型
     */
    ApiRelationModel insert(ApiRelationModel apiRelationModel);

    /**
     * 获取
     *
     * @param id api关系模型id
     */
    ApiRelationModel get(Long id);

    /**
     * 部分更新
     *
     * @param apiRelationModel api关系模型
     * @return 影响行数
     */
    int update(ApiRelationModel apiRelationModel);

    /**
     * 删除
     *
     * @param id api关系模型id
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 分页查询
     *
     * @param page             Page对象
     * @param apiRelationModel api关系模型
     * @return Page<ApiRelationModel>页面对象
     */
    Page<ApiRelationModel> page(Page<ApiRelationModel> page, ApiRelationModel apiRelationModel);

    /**
     * 生成项目代码
     *
     * @param id api关系模型id
     */
    JSONObject generateProjectCode(Long id, CgParam cgParam);
}
