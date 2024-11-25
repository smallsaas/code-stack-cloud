package com.jfeat.module.apicode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModelItem;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiRelationModelItemMapper;
import com.jfeat.module.apicode.services.gen.crud.service.CRUDApiRelationModelItemService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDApiRelationModelItemService
 * @author Code generator
 * @since 2023-12-04
 */

@Service
public class CRUDApiRelationModelItemServiceImpl  extends CRUDServiceOnlyImpl<ApiRelationModelItem> implements CRUDApiRelationModelItemService {





        @Resource
        protected ApiRelationModelItemMapper apiRelationModelItemMapper;

        @Override
        protected BaseMapper<ApiRelationModelItem> getMasterMapper() {
                return apiRelationModelItemMapper;
        }







}


