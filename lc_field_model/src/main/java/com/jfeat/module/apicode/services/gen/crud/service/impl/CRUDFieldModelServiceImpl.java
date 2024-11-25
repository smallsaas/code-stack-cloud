package com.jfeat.module.apicode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.apicode.services.gen.persistence.model.FieldModel;
import com.jfeat.module.apicode.services.gen.persistence.dao.FieldModelMapper;
import com.jfeat.module.apicode.services.gen.crud.service.CRUDFieldModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDFieldModelService
 * @author Code generator
 * @since 2021-10-20
 */

@Service
public class CRUDFieldModelServiceImpl  extends CRUDServiceOnlyImpl<FieldModel> implements CRUDFieldModelService {





        @Resource
        protected FieldModelMapper fieldModelMapper;

        @Override
        protected BaseMapper<FieldModel> getMasterMapper() {
                return fieldModelMapper;
        }







}


