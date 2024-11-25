package com.jfeat.module.apicode.services.domain.service.impl;
import com.jfeat.module.apicode.services.domain.service.ApiRelationModelItemService;
import com.jfeat.module.apicode.services.gen.crud.service.impl.CRUDApiRelationModelItemServiceImpl;
import org.springframework.stereotype.Service;
import com.jfeat.crud.plus.META;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("apiRelationModelItemService")
public class ApiRelationModelItemServiceImpl extends CRUDApiRelationModelItemServiceImpl implements ApiRelationModelItemService {

    @Override
    protected String entityName() {
        return "ApiRelationModelItem";
    }

    @Override
    protected String[] entityFeatures() {
        return new String[]{
            META.FEATURE_NONE
        };
    }

                            }
