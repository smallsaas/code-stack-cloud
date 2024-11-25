package com.jfeat.module.crudcode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFilters;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowFiltersMapper;
import com.jfeat.module.crudcode.services.gen.crud.service.CRUDLowFiltersService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDLowFiltersService
 * @author Code generator
 * @since 2021-09-06
 */

@Service
public class CRUDLowFiltersServiceImpl  extends CRUDServiceOnlyImpl<LowFilters> implements CRUDLowFiltersService {





        @Resource
        protected LowFiltersMapper lowFiltersMapper;

        @Override
        protected BaseMapper<LowFilters> getMasterMapper() {
                return lowFiltersMapper;
        }







}


