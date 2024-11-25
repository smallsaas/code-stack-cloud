package com.jfeat.module.apicode.services.gen.crud.service.impl;
// ServiceImpl start


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModel;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiRelationModelMapper;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiRelationModelItemMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModelItem;

import com.jfeat.module.apicode.services.gen.crud.service.CRUDApiRelationModelOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;

import javax.annotation.Resource;

import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.module.apicode.services.gen.crud.model.ApiRelationModelModel;

/**
 * <p>
 * implementation
 * </p>
 * CRUDApiRelationModelOverModelService
 *
 * @author Code generator
 * @since 2023-12-04
 */

@Service
public class CRUDApiRelationModelOverModelServiceImpl extends CRUDServiceOverModelImpl<ApiRelationModel, ApiRelationModelModel> implements CRUDApiRelationModelOverModelService {


    @Resource
    protected ApiRelationModelMapper apiRelationModelMapper;


    @Override
    protected BaseMapper<ApiRelationModel> getMasterMapper() {
        return apiRelationModelMapper;
    }

    @Override
    protected Class<ApiRelationModel> masterClassName() {
        return ApiRelationModel.class;
    }

    @Override
    protected Class<ApiRelationModelModel> modelClassName() {
        return ApiRelationModelModel.class;
    }


    @Resource
    private ApiRelationModelItemMapper apiRelationModelItemMapper;

    private final static String apiRelationModelItemFieldName = "relation_model_id:id";

    private final static String apiRelationModelItemKeyName = "relations";


    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                apiRelationModelItemKeyName
        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {


        if (field.compareTo(apiRelationModelItemKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(apiRelationModelItemFieldName);
            _field.setItemClassName(ApiRelationModelItem.class);
            _field.setItemMapper(apiRelationModelItemMapper);

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


