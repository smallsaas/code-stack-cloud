package com.jfeat.module.apicode.services.domain.service;

import com.jfeat.module.apicode.services.gen.crud.service.CRUDApiTableModelFieldService;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface ApiTableModelFieldService extends CRUDApiTableModelFieldService{

    /**
     * 获取字段列表
     *
     * @param tableModuleId 表id
     * @return 字段列表
     */
    List<ApiTableModelField> listField(Long tableModuleId);
}