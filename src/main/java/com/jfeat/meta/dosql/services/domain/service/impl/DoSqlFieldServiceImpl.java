package com.jfeat.meta.dosql.services.domain.service.impl;

import com.jfeat.meta.dosql.services.domain.service.DoSqlFieldService;
import com.jfeat.meta.dosql.services.gen.crud.service.impl.CRUDDoSqlFieldServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("DoSqlFieldService")
public class DoSqlFieldServiceImpl extends CRUDDoSqlFieldServiceImpl implements DoSqlFieldService {

    @Override
    protected String entityName() {
        return "DoSqlField";
    }


}
