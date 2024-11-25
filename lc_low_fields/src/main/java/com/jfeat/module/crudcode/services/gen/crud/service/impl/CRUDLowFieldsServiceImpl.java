package com.jfeat.module.crudcode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFields;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowFieldsMapper;
import com.jfeat.module.crudcode.services.gen.crud.service.CRUDLowFieldsService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDLowFieldsService
 * @author Code generator
 * @since 2021-10-09
 */

@Service
public class CRUDLowFieldsServiceImpl  extends CRUDServiceOnlyImpl<LowFields> implements CRUDLowFieldsService {





        @Resource
        protected LowFieldsMapper lowFieldsMapper;

        @Override
        protected BaseMapper<LowFields> getMasterMapper() {
                return lowFieldsMapper;
        }







}


