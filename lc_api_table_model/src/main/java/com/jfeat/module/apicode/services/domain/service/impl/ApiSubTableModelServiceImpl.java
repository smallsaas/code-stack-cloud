package com.jfeat.module.apicode.services.domain.service.impl;
import com.jfeat.module.apicode.services.domain.service.ApiSubTableModelService;
import com.jfeat.module.apicode.services.gen.crud.service.impl.CRUDApiSubTableModelServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("apiSubTableModelService")
public class ApiSubTableModelServiceImpl extends CRUDApiSubTableModelServiceImpl implements ApiSubTableModelService {

    @Override
    protected String entityName() {
        return "ApiSubTableModel";
    }



                            }
