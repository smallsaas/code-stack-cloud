package com.jfeat.module.crudcode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFieldProperties;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowFieldPropertiesMapper;
import com.jfeat.module.crudcode.services.gen.crud.service.CRUDLowFieldPropertiesService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDLowFieldPropertiesService
 * @author Code generator
 * @since 2021-08-31
 */

@Service
public class CRUDLowFieldPropertiesServiceImpl  extends CRUDServiceOnlyImpl<LowFieldProperties> implements CRUDLowFieldPropertiesService {





        @Resource
        protected LowFieldPropertiesMapper lowFieldPropertiesMapper;

        @Override
        protected BaseMapper<LowFieldProperties> getMasterMapper() {
                return lowFieldPropertiesMapper;
        }







}


