package com.jfeat.module.apicode.services.domain.service.impl;
import com.jfeat.module.apicode.services.domain.service.ApiRelationModelOverModelService;
import com.jfeat.module.apicode.services.gen.crud.service.impl.CRUDApiRelationModelOverModelServiceImpl;
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

@Service("apiRelationModelService")
public class ApiRelationModelOverModelServiceImpl extends CRUDApiRelationModelOverModelServiceImpl implements ApiRelationModelOverModelService {

    @Override
    protected String entityName() {
        return "ApiRelationModel";
    }

    @Override
    protected String[] entityFeatures() {
        return new String[]{
            META.FEATURE_NONE
        };
    }

                            }
