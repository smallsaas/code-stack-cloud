package com.jfeat.module.apicode.services.gen.crud.service.impl;
// ServiceImpl start


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiTableModelMapper;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiTableModelFieldMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;

import com.jfeat.module.apicode.services.gen.persistence.dao.ApiSubTableModelMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiSubTableModel;

import com.jfeat.module.apicode.services.gen.crud.service.CRUDApiTableModelOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;

import javax.annotation.Resource;

import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.module.apicode.services.gen.crud.model.ApiTableModelModel;

/**
 * <p>
 * implementation
 * </p>
 * CRUDApiTableModelOverModelService
 *
 * @author Code generator
 * @since 2023-11-22
 */

@Service
public class CRUDApiTableModelOverModelServiceImpl extends CRUDServiceOverModelImpl<ApiTableModel, ApiTableModelModel> implements CRUDApiTableModelOverModelService {


    @Resource
    protected ApiTableModelMapper apiTableModelMapper;


    @Override
    protected BaseMapper<ApiTableModel> getMasterMapper() {
        return apiTableModelMapper;
    }

    @Override
    protected Class<ApiTableModel> masterClassName() {
        return ApiTableModel.class;
    }

    @Override
    protected Class<ApiTableModelModel> modelClassName() {
        return ApiTableModelModel.class;
    }


    @Resource
    private ApiTableModelFieldMapper apiTableModelFieldMapper;

    private final static String apiTableModelFieldFieldName = "table_model_id:id";


    private final static String apiTableModelFieldKeyName = "fields";


    @Resource
    private ApiSubTableModelMapper apiSubTableModelMapper;

    private final static String apiSubTableModelFieldName = "table_model_id:id";


    private final static String apiSubTableModelKeyName = "subtables";


    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                apiTableModelFieldKeyName
                , apiSubTableModelKeyName
        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {


        if (field.compareTo(apiTableModelFieldKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(apiTableModelFieldFieldName);
            _field.setItemClassName(ApiTableModelField.class);
            _field.setItemMapper(apiTableModelFieldMapper);

            return _field;
        } else if (field.compareTo(apiSubTableModelKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(apiSubTableModelFieldName);
            _field.setItemClassName(ApiSubTableModel.class);
            _field.setItemMapper(apiSubTableModelMapper);

            return _field;
        }


        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
        };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {

        throw new BusinessException(BusinessCode.BadRequest);
    }


}


