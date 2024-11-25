package com.jfeat.module.apicode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiTableModelFieldMapper;
import com.jfeat.module.apicode.services.gen.crud.service.CRUDApiTableModelFieldService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDApiTableModelFieldService
 * @author Code generator
 * @since 2023-11-22
 */

@Service
public class CRUDApiTableModelFieldServiceImpl  extends CRUDServiceOnlyImpl<ApiTableModelField> implements CRUDApiTableModelFieldService {





        @Resource
        protected ApiTableModelFieldMapper apiTableModelFieldMapper;

        @Override
        protected BaseMapper<ApiTableModelField> getMasterMapper() {
                return apiTableModelFieldMapper;
        }







}


