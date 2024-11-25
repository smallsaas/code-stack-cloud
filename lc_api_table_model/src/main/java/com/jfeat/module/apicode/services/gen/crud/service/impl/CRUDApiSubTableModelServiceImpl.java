package com.jfeat.module.apicode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiSubTableModel;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiSubTableModelMapper;
import com.jfeat.module.apicode.services.gen.crud.service.CRUDApiSubTableModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDApiSubTableModelService
 * @author Code generator
 * @since 2023-11-22
 */

@Service
public class CRUDApiSubTableModelServiceImpl  extends CRUDServiceOnlyImpl<ApiSubTableModel> implements CRUDApiSubTableModelService {





        @Resource
        protected ApiSubTableModelMapper apiSubTableModelMapper;

        @Override
        protected BaseMapper<ApiSubTableModel> getMasterMapper() {
                return apiSubTableModelMapper;
        }







}


